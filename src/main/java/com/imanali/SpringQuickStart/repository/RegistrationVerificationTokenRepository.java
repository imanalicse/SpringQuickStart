package com.imanali.SpringQuickStart.repository;

import com.imanali.SpringQuickStart.model.Order;
import com.imanali.SpringQuickStart.model.RegistrationVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationVerificationTokenRepository extends JpaRepository<RegistrationVerificationToken, Long> {

}

