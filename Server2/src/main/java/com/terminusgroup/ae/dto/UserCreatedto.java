package com.terminusgroup.ae.dto;

;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terminusgroup.ae.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserCreatedto {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    private String email;

    @NotNull
    private String password;

    @JsonIgnore
    private Date createdAt = new Date();

    @JsonIgnore
    private Role role = new Role();
}
