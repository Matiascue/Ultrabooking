package com.usuarios.usuarios.services;

import com.usuarios.usuarios.dtos.newDto.NewUserDto;
import com.usuarios.usuarios.dtos.UserDto;
import com.usuarios.usuarios.dtos.updates.UpdateUser;

import java.util.List;

public interface UserService {
    UserDto registerUser(NewUserDto newUserDto);
    UserDto getUserById(Long id);
    UserDto updateUser(UpdateUser updateUser);
    UserDto deleteUser(Long id);
    List<UserDto>getAll();
}
