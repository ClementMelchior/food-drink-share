package com.idovia.jus_de_bagarre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private boolean securityEnabled=true;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        String[] staticResources = {
            "/css/**",
            "/img/**",
            "/icon/**",
            "/javascript/**",};

        if (securityEnabled) {
            http.authorizeRequests()
                .antMatchers("/login", "/error").permitAll()
                .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        } else {
            http.authorizeRequests()
                .antMatchers("/","/login", "/error", "/cocktails", "/vins", "/grailles", "/digestifs", "/h2-ui/**").permitAll() //设置首页默认映射
                .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/h2-ui/**")
                .and().headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        }
        
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build();
        UserDetails clement = User.withDefaultPasswordEncoder()
            .username("Clement")
            .password("clement")
            .roles("USER")
            .build();
        UserDetails marie = User.withDefaultPasswordEncoder()
            .username("Marie")
            .password("marie")
            .roles("USER")
            .build();
        UserDetails loann = User.withDefaultPasswordEncoder()
            .username("Loan")
            .password("loann")
            .roles("USER")
            .build();
        UserDetails alexis = User.withDefaultPasswordEncoder()
            .username("Alexis")
            .password("alexis")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, clement, marie, alexis, loann);
    }
}