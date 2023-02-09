package com.shop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .usernameParameter("memberid")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutUrl("/members/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))

        ;

        http.authorizeHttpRequests((requests) ->requests
                .requestMatchers("/css/**", "/js/**", "/img/**", "/fragments/**", "/layouts/**", "/favicon.ico", "/resources/**", "/error").permitAll()
                .requestMatchers("/", "/members/**", "/item/**", "/images/**", "/board/**", "/category/**").permitAll()
                .requestMatchers("/order/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
        ;

//        http.exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
