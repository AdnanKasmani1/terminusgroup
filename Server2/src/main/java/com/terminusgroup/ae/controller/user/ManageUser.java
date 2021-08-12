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

    @GetMapping("/listunresponsiveusers")
    public List<User> listunresponsiveusers()
    {
        List<User> u= userRepository.findAllByIsEnabledfalse();
        return u;
    }

    @Autowired
    UserService userService;
    @PostMapping("/acceptuser")
    public ResponseEntity<Message<String>> acceptuser(@RequestBody UserDto userDto)
    {
        try
        {
            Message<String> res = userService.responceuser(userDto.getEmail(),userDto.isEnable());
            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/deleteuser")
    public ResponseEntity<Message<String>> deleteuser(@RequestBody UserDto userDto)
    {
        try
        {
            Message<String> res = userService.deleteuser(userDto.getEmail(), userDto.isEnable());
            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/blockuser")
    public ResponseEntity<Message<String>> blockuser(@RequestBody UserDto userDto)
    {
        try
        {
            Message<String> res = userService.responceuser(userDto.getEmail(), userDto.isEnable());
            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/changepasswordbyadmin")
    public ResponseEntity<Message<String>> changepasswordbyadmin(@RequestBody UserDto userDto)
    {
        try
        {
            Message<String> res = userService.changepassword(userDto.getEmail(),userDto.getPassword());
            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/changepasswordbyuser")
    public ResponseEntity<Message<String>> changepasswordbyuser(@RequestBody UserDto userDto)
    {
        try
        {
            Message<String> res = userService.changepasswordbyuser(userDto.getEmail(),userDto.getOldpassword(),userDto.getNewpassword());
            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
