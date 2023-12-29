package com.imanali.SpringQuickStart.repository;


import com.imanali.SpringQuickStart.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    public PasswordResetToken findByUserId(Long id);

    PasswordResetToken findByToken(String token);
}
