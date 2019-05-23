package com.fontys.profiledoa.repository;

import com.fontys.profiledoa.model.Kweet;
import com.fontys.profiledoa.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.owner = :username")
    Profile getByUsername(@Param("username") String username);
}
