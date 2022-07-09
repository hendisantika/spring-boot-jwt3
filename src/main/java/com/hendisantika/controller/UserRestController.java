package com.hendisantika.controller;

import com.hendisantika.domain.User;
import com.hendisantika.dto.UserRequest;
import com.hendisantika.dto.UserResponse;
import com.hendisantika.service.UserService;
import com.hendisantika.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt3
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/9/22
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final JWTUtil util;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        Integer id = userService.saveUser(user);
        String message = "User with id '" + id + "' saved successfully!";
        //return new ResponseEntity<String>(message, HttpStatus.OK);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) {
        //Validate username/password with DB(required in case of Stateless Authentication)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = JWTUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new UserResponse(token, "Token generated successfully!"));
    }
}
