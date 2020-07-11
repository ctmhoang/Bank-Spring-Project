package com.ctmhoang.userfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UserFrontendApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(UserFrontendApplication.class, args);
    }

}
