package com.gamershop.shared;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.gamershop.shared"})
@ComponentScan(basePackages = {"com.gamershop.shared"})
public class SharedConfig {
}
