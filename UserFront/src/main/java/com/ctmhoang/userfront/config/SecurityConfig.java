package com.ctmhoang.userfront.config;

import com.ctmhoang.userfront.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private Environment env;

  @Autowired private UserSecurityService usc;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Internally generate random salt -> do not pass SALT in
                                        // here
  }

  public static final String[] PUBLIC_MATCHERS = {
    "/css/**", "/js/**", "/", "/images/**", "/error/**/*", "/signup"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
    http.csrf()
        .disable()
        .cors()
        .disable()
        .formLogin()
        .failureUrl("/index?error")
        .defaultSuccessUrl("/account")
        .loginPage("/index")
        .permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/index?logout")
        .deleteCookies("remember-me")
        .permitAll()
        .and()
        .rememberMe();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(usc).passwordEncoder(passwordEncoder());
  }
}
