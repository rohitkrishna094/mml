package com.rohit.mml.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.rohit.mml.model.Role;
import com.rohit.mml.model.RoleName;
import com.rohit.mml.model.User;
import com.rohit.mml.payload.request.LoginForm;
import com.rohit.mml.payload.request.SignUpForm;
import com.rohit.mml.payload.response.JwtResponse;
import com.rohit.mml.repository.RoleRepository;
import com.rohit.mml.repository.UserRepository;
import com.rohit.mml.security.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        JsonObject jo = new JsonObject();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            jo.addProperty("error", "Fail Username is already taken!");
            jo.addProperty("status", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<String>(jo.toString(), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(RoleName.ROLE_USER));
        user.setRoles(roles);

        user.setRoles(roles);
        userRepository.save(user);
        jo.addProperty("result", "User Registered");
        jo.addProperty("status", HttpStatus.OK.value());

        return ResponseEntity.ok().body(jo.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        // System.out.println(result);
        // System.out.println("----------------");
        // System.out.println(error.getDefaultMessage() + " : " + error.getCodes() + " : " + error.getCode());

        JsonObject jo = new JsonObject();
        jo.addProperty("status", HttpStatus.BAD_REQUEST.value());
        jo.addProperty("error", error.getDefaultMessage());
        return jo.toString();
    }

}
