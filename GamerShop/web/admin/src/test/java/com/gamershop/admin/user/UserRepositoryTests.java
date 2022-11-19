package com.gamershop.admin.user;

import com.gamershop.shared.entity.Role;
import com.gamershop.shared.entity.User;
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
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User kuku = new User("test", "test", "test", true);
        kuku.addRole(roleAdmin);

        User savedUser = repo.save(kuku);
        assertThat(savedUser.getUserID()).isGreaterThan(0);

    }

    @Test
    public void testCreateUserWithTwoRoles(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        Role roleSeller = entityManager.find(Role.class, 2);
        User piotr = new User("piotr", "test2", "test2", false);
        piotr.addRole(roleAdmin);
        piotr.addRole(roleSeller);

        User savedUser = repo.save(piotr);
        assertThat(savedUser.getUserID()).isGreaterThan(0);

    }
}
