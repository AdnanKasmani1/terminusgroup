package com.terminusgroup.ae.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.terminusgroup.ae.dto.UserDto;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.UserRepository;
import com.terminusgroup.ae.service.UserService;
import com.terminusgroup.ae.util.Message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register")
    public ResponseEntity<Message<String>> registeruser(@RequestBody @Valid UserDto user) {


        try {

            ObjectMapper objectMapper = new ObjectMapper();

            log.info("New User Registeration Request" + new Date()+"");
            log.info("User===> "+ user.getEmail());
            Message<String> res = userService.createUser(user);
            log.info("Registeration Result ===> "+ res.getStatus()+" "+res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }




}