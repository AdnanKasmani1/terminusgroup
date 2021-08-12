package com.terminusgroup.ae.controller.user;


import com.terminusgroup.ae.dto.UserCreatedto;
import com.terminusgroup.ae.dto.UserDto;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.UserRepository;
import com.terminusgroup.ae.service.UserService;
import com.terminusgroup.ae.util.Message.DTO;
import com.terminusgroup.ae.util.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Message<String>> registeruser(@RequestBody
            @DTO(UserCreatedto.class) User user) {


        try {





            Message<String> res = userService.createUser(user);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }




}