package com.imanali.SpringQuickStart.repository;

import com.imanali.SpringQuickStart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByLastnameNotNull();

    //JPQL
    @Query("SELECT u FROM User u where u.email = ?1")
    User getUserByEmailAddress(String emailAddress);

    // Native
    @Query(
            value = "SELECT * FROM user u where u.email = ?1",
            nativeQuery = true
    )
    User getUserByEmailAddressNative(String emailAddress);

    // Native Named Param
    @Query(
            value = "SELECT * FROM user u where u.email = :emailAddress",
            nativeQuery = true
    )
    User getUserByEmailAddressNativeNamedParam(@Param("emailAddress") String emailAddress);
}
