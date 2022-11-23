package com.gamershop.admin.user;

import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
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
    void testCreateUserWithTwoRoles(){
        RoleEntity roleAdmin = entityManager.find(RoleEntity.class, 1);
        RoleEntity roleSeller = entityManager.find(RoleEntity.class, 2);
        UserEntity piotr = new UserEntity("piotr", "test2", "test2", false);
        piotr.addRole(roleAdmin);
        piotr.addRole(roleSeller);

        UserEntity savedUser = repo.save(piotr);
        assertThat(savedUser.getUserId()).isPositive();

    }

    @Test
    void testGetUserByEmail(){
        String email = "moroz021291@gmail.com";
        UserEntity user = repo.getUserEntityByUserEmail(email).orElseGet(UserEntity::new);
        assertThat(user).isNotNull();
    }
}
