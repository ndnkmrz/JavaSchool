package com.gamershop.admin;

import com.gamershop.shared.SharedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={SharedConfig.class})
public class WebConfig {
}
