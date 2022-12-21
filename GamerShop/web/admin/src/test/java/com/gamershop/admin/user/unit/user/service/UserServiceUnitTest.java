package com.gamershop.admin.user.unit.user.service;

import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.admin.user.service.UserService;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import com.gamershop.shared.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    @Mock
    UserRepository userRepo;
    @Mock
    UserMapper userMapper;
    @Mock
    UserDTO userDTO;
    @InjectMocks
    UserService userService;


    @Test
    void testGetUserById(){
        Integer userId = 1;
        UserEntity userEntity = Mockito.mock(UserEntity.class);
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        doReturn(Optional.of(userEntity)).when(userRepo).findById(userId);
        when(userMapper.toDTO(userEntity)).thenReturn(userDTO);

        UserDTO found = userService.getUserById(userId);

        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo(userDTO.getUserName());
        assertThat(found.getUserEmail()).isEqualTo(userDTO.getUserEmail());

        verify(userRepo).findById(userId);
        verify(userMapper, times(1)).toDTO(userEntity);
    }

    @Test
    void testGetUserByEmail(){
        String userEmail = "bonya@gmail.com";
        UserEntity userEntity = Mockito.mock(UserEntity.class);
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        doReturn(Optional.of(userEntity)).when(userRepo).getUserEntityByUserEmail(userEmail);
        when(userMapper.toDTO(userEntity)).thenReturn(userDTO);

        UserDTO found = userService.getUserByEmail(userEmail);

        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo(userDTO.getUserName());
        assertThat(found.getUserEmail()).isEqualTo(userDTO.getUserEmail());

        verify(userRepo).getUserEntityByUserEmail(userEmail);
        verify(userMapper, times(1)).toDTO(userEntity);
    }

    @Test
    void testEncodePassword(){
        String rawPassword = "010989";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }


    @Test
    void testSaveUser(){
        UserService userService1 = Mockito.mock(UserService.class);
        userService1.saveUser(userDTO);
        verify(userService1, times(1)).saveUser(userDTO);
    }


}
