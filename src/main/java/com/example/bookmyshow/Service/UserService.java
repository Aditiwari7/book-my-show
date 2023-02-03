package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.UserRequestDto;
import com.example.bookmyshow.Models.UserEntity;
import com.example.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);
        } catch (Exception e){
            return "user could not be added";
        }
        return "User added successfully";
    }
}
