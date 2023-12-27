package com.imanali.SpringQuickStart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "registration_verification_tokens")
public class RegistrationVerificationToken {
    // 10 minutes
    private static final  int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Users_id_RegistrationVerificationTokens_user_id")
    )
    private User user;

    public RegistrationVerificationToken(User user, String token) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = calculationExpirationDate(EXPIRATION_TIME);
    }

    public RegistrationVerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculationExpirationDate(EXPIRATION_TIME);
    }

    private Date calculationExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
