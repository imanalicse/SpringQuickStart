package com.imanali.SpringQuickStart.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imanali.SpringQuickStart.config.JwtService;
import com.imanali.SpringQuickStart.event.RegistrationCompleteEvent;
import com.imanali.SpringQuickStart.exception.RecordNotFoundException;
import com.imanali.SpringQuickStart.model.PasswordResetToken;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.repository.PasswordResetTokenRepository;
import com.imanali.SpringQuickStart.repository.UserRepository;
import com.imanali.SpringQuickStart.token.Token;
import com.imanali.SpringQuickStart.token.TokenRepository;
import com.imanali.SpringQuickStart.token.TokenType;
import com.imanali.SpringQuickStart.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher publisher;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public AuthenticationResponse register(RegisterRequest registerRequest, HttpServletRequest request) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        var savedUser = userRepository.save(user);
        publisher.publishEvent(new RegistrationCompleteEvent(
                savedUser,
                applicationUrl(request)
        ));
        return generateTokenResponse(savedUser);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        return generateTokenResponse(user, true);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateAccessToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .tokenType("Bearer")
                        .expiresIn(jwtService.getAccessTokenExpiration())
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse generateTokenResponse(User user, Boolean revokeExistingToken) {
        if (revokeExistingToken) {
            revokeAllUserTokens(user);
        }
        return generateTokenResponse(user);
    }
    public AuthenticationResponse generateTokenResponse(User user) {
        var jwtToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiration())
                .refreshToken(refreshToken)
                .build();
    }

    public Object passwordResetRequest(PasswordResetModel passwordResetModel) throws RecordNotFoundException {
        User user = userRepository.findByEmail(passwordResetModel.getEmail()).orElseThrow(()-> new RecordNotFoundException("User not found"));
        PasswordResetToken existingPasswordResetToken = passwordResetTokenRepository.findByUserId(user.getId());
        if (existingPasswordResetToken != null) {
            passwordResetTokenRepository.delete(existingPasswordResetToken);
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
        String set_password_link = CommonUtil.getBaseUrl() + "/api/v1/auth/set-password/" + token;
        Map<String, Object> ob = new HashMap<>();
        ob.put("set_password_link", set_password_link);
        ob.put("expired_in", 30*60);
        return ob;
    }

    public String setNewPassword(String token, PasswordSetModel passwordSetModel) throws RecordNotFoundException {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            throw new RecordNotFoundException("Resent url not exists");
        }
        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(passwordSetModel.getNewPassword()));
        userRepository.save(user);
        return "New password has been saved successfully";
    }
}
