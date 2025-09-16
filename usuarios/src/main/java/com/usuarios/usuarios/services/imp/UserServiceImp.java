package com.usuarios.usuarios.services.imp;

import com.usuarios.usuarios.dtos.UserDto;
import com.usuarios.usuarios.dtos.newDto.NewUserDto;
import com.usuarios.usuarios.dtos.updates.UpdateUser;
import com.usuarios.usuarios.entitys.UserEntity;
import com.usuarios.usuarios.models.User;
import com.usuarios.usuarios.repository.UserRepository;
import com.usuarios.usuarios.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    @Override
    public UserDto registerUser(NewUserDto newUserDto) {
        Optional<UserEntity> found=this.userRepository.getByEmail(newUserDto.getEmail());
        if(found.isPresent()){
            logger.error("The email is in use: {}",newUserDto.getEmail());
            throw  new RuntimeException("The email is in use");
        }
        User user=modelMapper.map(newUserDto, User.class);
        user.setActive(true);
        UserEntity userSaved=modelMapper.map(user, UserEntity.class);
        return modelMapper.map(this.userRepository.save(userSaved), UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity=this.userRepository.findById(id).orElseThrow(()->{
            logger.error("The user not found with id: {}",id);
            return new RuntimeException("The user not found");
        });
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(UpdateUser updateUser) {
        UserEntity userEntity=this.userRepository.findById(updateUser.getId()).orElseThrow(()->{
            logger.error("The user not found with id: {}",updateUser.getId());
            return new RuntimeException("The user not found");
        });
        if(!userEntity.getEmail().equals(updateUser.getEmail())) {
            Optional<UserEntity> found = this.userRepository.getByEmail(updateUser.getEmail());
            if (found.isPresent()) {
                logger.error("The email is in use: {}", updateUser.getEmail());
                throw new RuntimeException("The email is in use");
            }
        }
        userEntity=modelMapper.map(updateUser, UserEntity.class);
        return modelMapper.map(this.userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto deleteUser(Long id) {
        UserEntity userEntity=this.userRepository.findById(id).orElseThrow(()->{
            logger.error("The user not found with id: {}",id);
            return new RuntimeException("The user not found");
        });
        userEntity.setActive(false);
        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity>usersEntity=userRepository.findAll();
        return usersEntity.stream().map(userEntity ->
                modelMapper.map(userEntity, UserDto.class)).toList();
    }
}
