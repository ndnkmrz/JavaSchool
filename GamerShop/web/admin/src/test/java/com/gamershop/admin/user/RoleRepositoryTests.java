package com.gamershop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import com.gamershop.shared.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateAdmin(){
        Role roleAdmin = new Role("Admin");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getRoleID()).isGreaterThan(0);


    }

    @Test
    public void testCreateSeller(){
        Role roleSeller = new Role("Seller");
        Role savedRole = repo.save(roleSeller);
        assertThat(savedRole.getRoleID()).isGreaterThan(0);


    }


}
