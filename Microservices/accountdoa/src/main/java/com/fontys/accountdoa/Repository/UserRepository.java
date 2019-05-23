package com.fontys.accountdoa.Repository;

import com.fontys.accountdoa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.hashedPassword = :hashed")
    User login(@Param("username") String username, @Param("hashed") String hashed);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username like :partialname")
    List<User> findPartialUsername(@Param("partialname") String partialname);
}
