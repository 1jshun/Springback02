package com.example.demo.user;


import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.user.model.UserDto;
import com.example.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto.SignupReq dto) {
        userService.signup(dto);
        return ResponseEntity.ok("성공");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto.LoginReq dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword(), null);

        Authentication authentication = authenticationManager.authenticate(token);
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        if(user != null) {
            String jwt = jwtUtil.createToken(user.getIdx(), user.getUsername(), user.getRole());
            return ResponseEntity.ok().header("Set-Cookie", "ATOKEN=" + jwt + "; Path=/").build();
        }

        return ResponseEntity.ok("로그인 실패");
    }
}
