package com.gamershop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan({"com.gamershop.shared.entity", "com.gamershop.admin.user"})
public class GamerShopAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamerShopAdminApplication.class, args);
    }

}
