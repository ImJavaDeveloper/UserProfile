package com.user.profile.controller;

import com.user.profile.model.PasswordUpdateRequest;
import com.user.profile.model.UserRegistrationRequest;
import com.user.profile.model.UserUpdateRequest;
import com.user.profile.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest)
    {
        userProfileService.registerUser(userRegistrationRequest);
        return new ResponseEntity<>(userRegistrationRequest,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest)
    {
       UserUpdateRequest userUpdated= userProfileService.updateUser(userUpdateRequest);
        return ResponseEntity.of(Optional.of(userUpdated));
    }

    @PutMapping("/reset/password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest)
    {
        PasswordUpdateRequest passUpdated=userProfileService.updatePassword(passwordUpdateRequest);
        return ResponseEntity.of(Optional.of(passUpdated));
    }
}
