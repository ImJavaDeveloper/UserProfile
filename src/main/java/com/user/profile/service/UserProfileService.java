package com.user.profile.service;

import com.user.profile.entity.UserProfile;
import com.user.profile.model.PasswordUpdateRequest;
import com.user.profile.model.UserRegistrationRequest;
import com.user.profile.model.UserUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface UserProfileService {

    UserRegistrationRequest registerUser(UserRegistrationRequest userRegistrationRequest);
    UserUpdateRequest updateUser(UserUpdateRequest userUpdateRequest);
    PasswordUpdateRequest updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
