package com.terminusgroup.ae.service;

import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.util.Message.Message;

public interface iUserService {
    Message<String> createUser(User user) throws Exception;
    Message<String> responceuser(String email, boolean enable) throws Exception;
    Message<String> deleteuser(String email , boolean enable) throws Exception;
    Message<String> changepassword(String email, String passsword) throws Exception;
    Message<String> changepasswordbyuser(String email , String oldpassword , String newpassword) throws Exception;

}
