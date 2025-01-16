package com.user.profile.repository;

import com.user.profile.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

    UserCredential findByUsername(String username);
}
