package colombia.authservice.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import colombia.authservice.Security.jwt.JwtEntryPoint;
import colombia.authservice.Security.jwt.JwtTokenFilter;


@Configuration
@EnableWebSecurity
public class MainSecurity {
    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->{
                        try {
                            authorize
                            .requestMatchers("/auth/**").permitAll() // INGRESO PUBLICO
                            // .requestMatchers("/admin/**").hasRole("ADMIN")
                            // .requestMatchers("/db/**").access(new
                            // WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                            // .requestMatchers("/db/**").access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"),
                            // AuthorityAuthorizationManager.hasRole("DBA")))
                            .anyRequest().authenticated().and()
                            .exceptionHandling(handling -> handling.authenticationEntryPoint(jwtEntryPoint))
                            .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                });
        http.cors((cors) -> cors.disable()).csrf((csrf) -> csrf.disable());
        return http.build();
    }

}