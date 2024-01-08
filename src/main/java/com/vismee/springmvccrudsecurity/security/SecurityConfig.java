package com.vismee.springmvccrudsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails john = User.builder().
                           username("john").
                           password("{bcrypt}$2a$10$zAUdQpruJeYh11aPJskHDOPF/hIFkbv4eWmOXBYAArRdrvKng64sq").
                           roles("EMPLOYEE").
                           build();

        UserDetails mary = User.builder().
                           username("mary").
                           password("{bcrypt}$2a$10$lLVIF796ANLLCtPm2apfd.qrBMM99UzLAt5.Bm/P6jjbAFAslYkjm").
                           roles("MANAGER").
                           build();

        UserDetails susan = User.builder().
                            username("susan").
                            password("{bcrypt}$2a$10$PldjssY3mOsqc50WPCCZKuH6jKnrXDGnQDL6NLp8aDqM9ZvfgQL1.").
                            roles("ADMIN").
                            build();

        return new InMemoryUserDetailsManager(john,mary,susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.
                authorizeHttpRequests(configurer -> configurer.
                        requestMatchers("/employees/showFormForAdd/**").hasAnyRole("MANAGER","ADMIN").
                        requestMatchers("/employees/showFormForUpdate/**").hasAnyRole("MANAGER","ADMIN").
                        requestMatchers("/employees/delete").hasRole("ADMIN").
                        anyRequest().authenticated()).
                formLogin(form -> form.loginPage("/showLogin").loginProcessingUrl("/authenticateUser").permitAll()).
                logout(logout -> logout.permitAll()).
                exceptionHandling(configurer -> configurer.accessDeniedPage("/accessDenied"));

        return http.build();
    }
}
