package com.usuarios.usuarios.services.imp;

import com.usuarios.usuarios.builder.GenericBuilder;
import com.usuarios.usuarios.dtos.UserDto;
import com.usuarios.usuarios.dtos.newDto.NewUserDto;
import com.usuarios.usuarios.dtos.updates.UpdateUser;
import com.usuarios.usuarios.entitys.UserEntity;
import com.usuarios.usuarios.models.User;
import com.usuarios.usuarios.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private UserServiceImp userServiceImp;

    private UserEntity userEntity;
    private User user;
    private UserDto userDto;
    private NewUserDto newUserDto;
    private UpdateUser updateUser;
    private UserEntity deletedUser;
    private UpdateUser userEmailChange;
    private UserEntity userEntityEmail;
    private List<UserEntity>userEntities;
    private List<UserDto>userDtos;
    @BeforeEach
    public void setUp(){
        userEntity=new GenericBuilder<>(UserEntity.class).with(u->{
         u.setId(1L);
         u.setEmail("juansito@gmail.com");
         u.setPassword("juan123");
         u.setName("Juan");
         u.setActive(true);
        }).build();
        user=new GenericBuilder<>(User.class).with(u->{
            u.setId(1L);
            u.setEmail("juansito@gmail.com");
            u.setPassword("juan123");
            u.setName("Juan");
        }).build();

        userDto=new GenericBuilder<>(UserDto.class).with(u->{
            u.setId(1L);
            u.setEmail("juansito@gmail.com");
            u.setName("Juan");
            u.setActive(true);
        }).build();
        newUserDto=new GenericBuilder<>(NewUserDto.class).with(n->{
            n.setEmail("juansito@gmail.com");
            n.setPassword("juan123");
            n.setName("Juan");
        }).build();
        updateUser=new GenericBuilder<>(UpdateUser.class).with(u->{
            u.setId(1L);
            u.setEmail("juansito@gmail.com");
            u.setPassword("juan12354");
            u.setName("Juan");
        }).build();
        deletedUser=new GenericBuilder<>(UserEntity.class).with(u->{
            u.setId(1L);
            u.setEmail("juansito@gmail.com");
            u.setPassword("juan123");
            u.setName("Juan");
            u.setActive(false);
        }).build();
        userEmailChange=new GenericBuilder<>(UpdateUser.class).with(u->{
            u.setId(1L);
            u.setEmail("juansitwqeo@gmail.com");
            u.setPassword("juan12354");
            u.setName("Juan");
        }).build();
        userEntityEmail=new GenericBuilder<>(UserEntity.class).with(u->{
            u.setId(2L);
            u.setEmail("juansitwqeo@gmail.com");
            u.setPassword("juan123");
            u.setName("Juan");
            u.setActive(true);
        }).build();
        userEntities=new ArrayList<>();
        userEntities.add(userEntity);
        userDtos=new ArrayList<>();
        userDtos.add(userDto);
    }
    @Test
    void registerUserSuccess() {
        Mockito.when(userRepository.getByEmail("juansito@gmail.com")).thenReturn(Optional.empty());
        Mockito.when(mapper.map(newUserDto, User.class)).thenReturn(user);
        Mockito.when(mapper.map(user, UserEntity.class)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(mapper.map(userEntity,UserDto.class)).thenReturn(userDto);
        UserDto response=userServiceImp.registerUser(newUserDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals("Juan",response.getName());
        Assertions.assertEquals(userEntity.getEmail(),response.getEmail());
    }

    @Test
    void getUserByIdSuccess() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(mapper.map(userEntity, UserDto.class)).thenReturn(userDto);
        UserDto response=userServiceImp.getUserById(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals(userEntity.getName(),response.getName());
        Assertions.assertEquals(userEntity.getEmail(),response.getEmail());
    }

    @Test
    void updateUserSuccess() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(mapper.map(updateUser, UserEntity.class)).thenReturn(userEntity);
        Mockito.when(mapper.map(userEntity, UserDto.class)).thenReturn(userDto);
        UserDto response=userServiceImp.updateUser(updateUser);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals(userEntity.getEmail(),response.getEmail());
    }

    @Test
    void deleteUserSuccess() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(userEntity)).thenReturn(deletedUser);
        Mockito.when(mapper.map(deletedUser, UserDto.class)).thenReturn(userDto);
        UserDto response=userServiceImp.deleteUser(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals(userEntity.getEmail(),response.getEmail());
    }
    @Test
    void getUserByIdNoSucces(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            userServiceImp.getUserById(1L);
        });
        Assertions.assertEquals("The user not found",response.getMessage());
    }
    @Test
    void deletedUserNoSucces(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            userServiceImp.deleteUser(1L);
        });
        Assertions.assertEquals("The user not found",response.getMessage());
    }
    @Test
    void registerUserEmailExist(){
        Mockito.when(userRepository.getByEmail("juansito@gmail.com")).thenReturn(Optional.of(userEntity));
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            userServiceImp.registerUser(newUserDto);
        });
        Assertions.assertEquals("The email is in use",response.getMessage());
    }
    @Test
    void updateUserNotFound(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            userServiceImp.updateUser(updateUser);
        });
        Assertions.assertEquals("The user not found",response.getMessage());
    }
    @Test
    void updateUserEmailExist(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.getByEmail("juansitwqeo@gmail.com")).thenReturn(Optional.of(userEntityEmail));
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            userServiceImp.updateUser(userEmailChange);
        });
        Assertions.assertEquals("The email is in use",response.getMessage());
    }
    @Test
    void getAll(){
        Mockito.when(userRepository.findAll()).thenReturn(userEntities);
        Mockito.when(mapper.map(userEntity, UserDto.class)).thenReturn(userDto);
        List<UserDto>response=userServiceImp.getAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(userDtos.size(),response.size());
        Assertions.assertEquals(userDtos.get(0),response.get(0));
    }
}