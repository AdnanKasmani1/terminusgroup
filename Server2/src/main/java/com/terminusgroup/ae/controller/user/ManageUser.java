package com.terminusgroup.ae.controller.user;


import com.terminusgroup.ae.dto.UserDto;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.RoleRepository;
import com.terminusgroup.ae.repository.UserRepository;
import com.terminusgroup.ae.service.UserService;
import com.terminusgroup.ae.util.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2.0/users")
public class ManageUser {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("/listapproveusers")
    public List<User> listusers()
    {
        List<User> u = userRepository.findAllByIsenabletrue();
        return u;
    }













}
