package com.imanali.SpringQuickStart.auth;

import com.imanali.SpringQuickStart.exception.RecordNotFoundException;
import com.imanali.SpringQuickStart.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest registerRequest, final HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.register(registerRequest, request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @PostMapping("/password-reset-request")
    public Object passwordResetRequest(@RequestBody PasswordResetModel passwordResetModel) throws RecordNotFoundException {
        return authenticationService.passwordResetRequest(passwordResetModel);
    }

    @PostMapping("/set-password/{token}")
    public String setPassword(@PathVariable("token") String token, @RequestBody PasswordSetModel passwordSetModel) throws RecordNotFoundException {
        return authenticationService.setNewPassword(token, passwordSetModel);
    }
}
