package com.gamershop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderTest {
//    private final UserRepository repo;
//    public PasswordEncoderTest(UserRepository repo){
//        this.repo = repo;
//    }
//
//    @Test
//    public void testCreateAndCheck(){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        UserEntity user = repo.getUserEntityByUserEmail("moroz021291@gmail.com").orElseThrow(() -> new UserNotFoundException("User not found"));
//        String encodedPass = user.getPasswordHash();
//        String rawPass = "010989Bill";
//        boolean matches = passwordEncoder.matches(rawPass, encodedPass);
//        assertThat(matches).isTrue();
//
//    }
    @Test
    public void testEncodePassword(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "010989";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();

    }
}
