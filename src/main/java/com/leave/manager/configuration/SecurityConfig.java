package com.leave.manager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider().setPasswordEncoder(passwordEncoder());
        authProvider().setUserDetailsService(userDetailsService);
        return authProvider;
    }

        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    //Authorization information
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .antMatcher("/api/**").authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                    .httpBasic();
    }

    //TODO Argon2PasswordEncoder - BouncyCastle package

    // Authentication information
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("cscs")
////                .password("user")
//                .password("$2y$10$AZEHIqmiP5A5Dx1Vb2Wp2OSpsLpjqNY47bblHeALaHVkBri5myZDK")
//                .roles("USER");
//        auth.inMemoryAuthentication().withUser("admin")
////                .password("admin")
//                .password("$2y$12$VjalTku4w3onQk8CnKCL/Oq3AwDas5CUoQMT5U5Tjc0dvut00mA9O")
//                .roles("ADMIN");
//    }

}
