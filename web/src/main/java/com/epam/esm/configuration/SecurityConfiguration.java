package com.epam.esm.configuration;

import com.epam.esm.security.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Autowired
    public SecurityConfiguration(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/gifts").permitAll()
                .antMatchers(HttpMethod.POST, "/userOrders").hasRole(USER) //work
                .antMatchers(HttpMethod.GET, "/tags", "/users", "/userOrders").hasAnyRole(USER, ADMIN) //work
                .antMatchers(HttpMethod.POST, "/gifts", "/tags").hasRole(ADMIN) //work
                /*.antMatchers(HttpMethod.DELETE, "/gifts", "/tags", "/users", "/userOrders").hasRole(ADMIN)*/
                /*.antMatchers(HttpMethod.PATCH, "/gifts").hasRole(ADMIN)*/
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer);
    }
}