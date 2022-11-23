package com.gamershop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamershop.admin.user.repo.RoleRepository;
import com.gamershop.shared.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTests {

    private final RoleRepository repo;
    @Autowired
    public RoleRepositoryTests(RoleRepository _repo){
        this.repo = _repo;
    }

    @Test
    void testCreateAdmin(){
        RoleEntity roleAdmin = new RoleEntity("Admin");
        RoleEntity savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getRoleId()).isPositive();


    }

    @Test
    void testCreateSeller(){
        RoleEntity roleSeller = new RoleEntity("Seller");
        RoleEntity savedRole = repo.save(roleSeller);
        assertThat(savedRole.getRoleId()).isPositive();


    }


}
