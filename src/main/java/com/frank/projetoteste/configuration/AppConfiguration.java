package com.frank.projetoteste.configuration;

import com.frank.projetoteste.repository.UserRepository;
import com.frank.projetoteste.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {



    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
        return new UserRepository(jdbcTemplate);
    }

}
