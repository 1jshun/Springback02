package com.example.demo.user;


import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void signup(UserDto.SignupReq dto) {
        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    public UserDto.LoginRes login(UserDto.LoginReq dto) throws EmptyResultDataAccessException {
        Optional<User> result = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(result.isPresent()) {
            User user = result.get();
            return UserDto.LoginRes.from(user);
        }
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserService 실행됨");

        User user = userRepository.findByEmail(username).orElseThrow();

        return AuthUserDetails.from(user);
    }

}
