package com.user.profile.service;

import com.user.profile.entity.UserCredential;
import com.user.profile.entity.UserProfile;
import com.user.profile.exception.InvalidPasswordException;
import com.user.profile.exception.UserAlreadyExistException;
import com.user.profile.exception.UserNotFoundException;
import com.user.profile.kafka.event.UserPasswordChangeEvent;
import com.user.profile.kafka.service.KafkaMessageProducer;
import com.user.profile.kafka.event.UserRegistrationEvent;
import com.user.profile.model.PasswordUpdateRequest;
import com.user.profile.model.UserRegistrationRequest;
import com.user.profile.model.UserUpdateRequest;
import com.user.profile.repository.UserCredentialRepository;
import com.user.profile.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    KafkaMessageProducer kafkaMessageProducer;
    @Autowired
    ModelMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserRegistrationRequest registerUser(UserRegistrationRequest userRegistrationRequest) {

        UserProfile userProfile=userProfileRepository.findByUsername(userRegistrationRequest.getUsername());
        if(userProfile == null)
        {
            UserProfile registereUserProfile=mapper.map(userRegistrationRequest,UserProfile.class);
            UserCredential userCredential=mapper.map(userRegistrationRequest, UserCredential.class);
            String encodedPassword=passwordEncoder.encode(userCredential.getPassword());
            userCredential.setPassword(encodedPassword);
            userProfileRepository.save(registereUserProfile);
            userCredentialRepository.save(userCredential);
            kafkaMessageProducer.sendUserRegistrationEvent(new UserRegistrationEvent(
                    userRegistrationRequest.getUsername(),userCredential.getPassword()
            ));
        }
        else {
            log.info("User Already Exist:{}",userRegistrationRequest.getUsername());
            throw new UserAlreadyExistException("UserAlreadyExistException !!");
        }

        return userRegistrationRequest;
    }

    @Override
    public UserUpdateRequest updateUser(UserUpdateRequest userUpdateRequest) {
        log.info("Updating User:{}",userUpdateRequest.toString());
        UserProfile userProfile=userProfileRepository.findByUsername(userUpdateRequest.getUsername());
        UserProfile userProfileToBeUpdated=null;

        if(userProfile != null)
        {
            userProfileToBeUpdated=mapper.map(userUpdateRequest,UserProfile.class);
            userProfileToBeUpdated.setUid(userProfile.getUid());
            log.info("userProfileToBeUpdated:{}",userProfileToBeUpdated);
            userProfileRepository.save(userProfileToBeUpdated);
        }
        else {
            log.info("User Not Found In Database:{}",userUpdateRequest.getUsername());
            throw new UserNotFoundException("UserNotFoundException !!");
        }

        return userUpdateRequest;
    }

    @Override
    public PasswordUpdateRequest updatePassword(PasswordUpdateRequest passwordUpdateRequest) {

        UserCredential userCredential=userCredentialRepository.findByUsername(passwordUpdateRequest.getUsername());
        if(userCredential == null)
        {
            throw  new UserNotFoundException("User Does Not Exist !!");
        }
        if(passwordEncoder.matches(passwordUpdateRequest.getOldPassword(),userCredential.getPassword()))
        {
            log.info(userCredential.toString());
            log.info(passwordEncoder.encode(passwordUpdateRequest.getOldPassword()));
            UserCredential userCredWithNewPass=mapper.map(passwordUpdateRequest,UserCredential.class);
            String encodePassword=passwordEncoder.encode(passwordUpdateRequest.getPassword());
            userCredWithNewPass.setPassword(encodePassword);
            userCredentialRepository.save(userCredWithNewPass);
            kafkaMessageProducer.sendUserPasswordChangeEvent(new UserPasswordChangeEvent(userCredWithNewPass.getUsername(),userCredWithNewPass.getPassword()));
        }
        else
        {
            throw new InvalidPasswordException("Old Password Is Invalid");
        }

         return passwordUpdateRequest;
    }
}
