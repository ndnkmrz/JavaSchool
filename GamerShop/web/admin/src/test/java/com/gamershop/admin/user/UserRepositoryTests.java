package com.gamershop.admin.user;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.admin.user.service.UserService;
import com.gamershop.shared.SharedConfig;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;


import javax.management.relation.Role;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(value={SharedConfig.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class UserRepositoryTests {
    private final UserRepository repo;
    private final TestEntityManager entityManager;
    @Autowired
    public UserRepositoryTests(UserRepository _repo, TestEntityManager _entityManager){
        this.repo = _repo;
        this.entityManager = _entityManager;
    }

    @Test
    void testCreateUser(){
        RoleEntity roleAdmin = entityManager.find(RoleEntity.class, 1);
        UserEntity kuku = new UserEntity("test", "test", "test", true);
        kuku.addRole(roleAdmin);
        UserEntity savedUser = repo.save(kuku);
        assertThat(savedUser.getUserId()).isPositive();

    }


    @Test
    public void testCreateAndCheck(){
        RoleEntity roleAdmin = entityManager.find(RoleEntity.class, 2);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<RoleEntity> roles = Arrays.asList(roleAdmin);
        UserEntity user = new UserEntity(null, "piotr1@gmail.com", passwordEncoder.encode("010989Bill"), "piotr1", true, roles);
        UserEntity savedUser = repo.save(user);
        String encodedPass = savedUser.getPasswordHash();
        String rawPass = "010989Bill";
        boolean matches = passwordEncoder.matches(rawPass, encodedPass);
        assertThat(matches).isTrue();

    }


}
