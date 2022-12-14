package com.gamershop.customer;

import com.gamershop.customer.customer.repo.UserRepository;
import com.gamershop.shared.SharedConfig;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(value={SharedConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTests {
    private final UserRepository repo;
    private final TestEntityManager entityManager;
    @Autowired
    public UserTests(UserRepository repo, TestEntityManager entityManager){
        this.repo = repo;
        this.entityManager = entityManager;
    }
    @Test
    public void testCreateAndCheck(){
        RoleEntity roleAdmin = entityManager.find(RoleEntity.class, 2);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<RoleEntity> roles = Arrays.asList(roleAdmin);
        UserEntity user = new UserEntity(null, "piotr@gmail.com", passwordEncoder.encode("010989Bill"), "piotr", true, roles);
        UserEntity savedUser = repo.save(user);
        String encodedPass = savedUser.getPasswordHash();
        String rawPass = "010989Bill";
        boolean matches = passwordEncoder.matches(rawPass, encodedPass);
        assertThat(matches).isTrue();

    }
}
