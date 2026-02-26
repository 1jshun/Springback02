package com.example.demo.user;


import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto.SignupReq dto) {
        userRepository.save(dto.toEntity());
    }

    public UserDto.LoginRes login(UserDto.LoginReq dto) throws EmptyResultDataAccessException {
        Optional<User> result = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(result.isPresent()) {
            User user = result.get();
            return UserDto.LoginRes.from(user);
        }
        return null;
    }
}
