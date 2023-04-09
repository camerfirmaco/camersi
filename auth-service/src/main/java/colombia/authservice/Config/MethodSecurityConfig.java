package colombia.authservice.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(
  securedEnabled = true, 
  jsr250Enabled = true
)
public class MethodSecurityConfig {
  
}
