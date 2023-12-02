package com.imanali.SpringQuickStart.repository;

import com.imanali.SpringQuickStart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
