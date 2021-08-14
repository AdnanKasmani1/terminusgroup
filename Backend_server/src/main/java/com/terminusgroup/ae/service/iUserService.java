package com.terminusgroup.ae.service;

import com.terminusgroup.ae.dto.UserDto;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.util.Message.Message;

public interface iUserService {
    Message<String> createUser(UserDto userDto) throws Exception;

}
