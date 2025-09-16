package com.usuarios.usuarios.dtos;

import lombok.*;

@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;

    public Long getId(){
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserDto(Long id, String name, String email,String password, boolean active) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.active=active;
        this.password=password;
    }
    public UserDto(){

    }

}
