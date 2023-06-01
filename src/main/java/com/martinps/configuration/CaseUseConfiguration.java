package com.martinps.configuration;

import com.martinps.caseuse.GetUser;
import com.martinps.caseuse.GetUserImplement;
import com.martinps.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
