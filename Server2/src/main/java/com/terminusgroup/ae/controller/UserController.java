package com.terminusgroup.ae.controller;


import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.RoleRepository;
import com.terminusgroup.ae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2.0/users")
public class UserController {


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
