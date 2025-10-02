package com.ms.user.services;

import com.ms.user.dtos.UserDto;
import com.ms.user.models.User;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public User create(UserDto userDto){
        var user =  new User();
        BeanUtils.copyProperties(userDto, user);
        user =  userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
