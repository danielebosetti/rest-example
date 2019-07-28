package my.test.restweb.config;

import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import my.test.restweb.security.SimpleCorsFilter;

@Configuration
public class SecurityConfig {

  @Bean
  public Filter corsFilter() {
    return new SimpleCorsFilter();
  }
}
