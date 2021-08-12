package com.terminusgroup.ae.service;


import com.terminusgroup.ae.model.Role;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.RoleRepository;
import com.terminusgroup.ae.repository.UserRepository;
import com.terminusgroup.ae.util.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService implements iUserService , UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Message<String> createUser(User userdto) throws Exception {
        try {
            User user = new User();
            String password = userdto.getPassword();
           // System.out.println("pass=>"+password);
            String hash = new BCryptPasswordEncoder().encode(password);
            String userEmail = userdto.getEmail();
            Long userId;
            user.setPassword(hash);
            user.setEmail(userEmail);
            user.setName(userdto.getName());
            Role role = roleRepository.findOneById(2L);
            user.setRole(role);
            User callUser = userRepository.findOneByEmail(userEmail);
            userRepository.saveAndFlush(user);
            callUser = userRepository.findOneByEmail(userEmail);

            if (callUser == null) {

                Message<String> messages = new Message<>();
                messages.setMessage("user could not fetched successfully")
                        .setStatus(500)
                        .setCode("unsuccessful");
                return messages;
            } else {
                userId = callUser.getId();
            }
            Message<String> messages = new Message<>();
            userId = callUser.getId();


            messages.setData(userId.toString())
                    .setMessage("user created successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("This email  already exist."
                 ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> responceuser(String email,boolean enable) throws Exception {
        try {
            User callUser = userRepository.findOneByEmail(email);
            callUser.setEnable(enable);
            userRepository.saveAndFlush(callUser);


            Long userId = callUser.getId();

            Message<String> messages = new Message<>();
            messages.setData(userId.toString())
                    .setMessage("user  updated successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error Occured."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> deleteuser(String email, boolean enable) throws Exception {
        try {
            User callUser = userRepository.findOneByEmail(email);
            userRepository.delete(callUser);
            Message<String> messages = new Message<>();
            messages.setData("2")
                    .setMessage("user delete successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error user not deleted."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> changepassword(String email , String passsword) throws Exception {

        try {
            User callUser = userRepository.findOneByEmail(email);
            String hash = new BCryptPasswordEncoder().encode(passsword);
            callUser.setPassword(hash);
            userRepository.saveAndFlush(callUser);
            Message<String> messages = new Message<>();
            messages.setData("2")
                    .setMessage("password change  successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error password not change. "
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }

    }

    @Override
    public Message<String> changepasswordbyuser(String email , String oldpassword, String newpassword) throws Exception {
        try {
            Message<String> messages;
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User callUser = userRepository.findOneByEmail(email);
          //  String hash = new BCryptPasswordEncoder().encode(oldpassword);
            String password = callUser.getPassword();
           // System.out.println(hash);
           // System.out.printf(password);
            if(!bCryptPasswordEncoder.matches(oldpassword, password))
            {
               messages = new Message<>();
                messages.setData("2")
                        .setMessage("Old password incorrect")
                        .setStatus(200)
                        .setCode("unsuccessful");

            }
            else {
                String np = new BCryptPasswordEncoder().encode(newpassword);
                 callUser.setPassword(np);
                userRepository.saveAndFlush(callUser);
             messages = new Message<>();
                messages.setData("2")
                        .setMessage("password change  successfully")
                        .setStatus(200)
                        .setCode("successful");
            }


            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error password not change  ."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }




    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  userRepository.findOneByEmail(s);
        if(user == null)
        {
            return null;
        }
        return user;
    }







}
