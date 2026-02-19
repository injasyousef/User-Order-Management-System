package com.example.user_order_management_system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static com.example.user_order_management_system.user.Role.ADMIN;
import static com.example.user_order_management_system.user.Role.CUSTOMER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                    //users
                    .requestMatchers(HttpMethod.GET,"/users").hasAnyAuthority(ADMIN.name(), CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/users/login").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/users/{id}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.POST,"/users").hasAnyAuthority(ADMIN.name(), CUSTOMER.name())
                    .requestMatchers(HttpMethod.PUT,"/users/{id}").hasAnyAuthority(ADMIN.name(), CUSTOMER.name() )
                    .requestMatchers(HttpMethod.DELETE,"/users/{id}").hasAnyAuthority(ADMIN.name(), CUSTOMER.name())
                    .requestMatchers(HttpMethod.POST,"/users/signup").permitAll()
                    .requestMatchers(HttpMethod.PUT,"/users/{id}/changePassword").hasAnyAuthority(ADMIN.name() ,CUSTOMER.name() )

                    // 📦 Orders Endpoints
                    .requestMatchers("/api/orders/**")
                    .hasAnyAuthority(ADMIN.name(), CUSTOMER.name())

                    .anyRequest().authenticated())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
