package cl.govegan.ms_comment_rating.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) { 
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:8100", "http://localhost:8082")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        .allowedHeaders("*").allowCredentials(true);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/api/v1/comments/status").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ratings/status").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/comments").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/ratings/add").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/comments/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/comments/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/comments/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/ratings/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/ratings/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/ratings/**").permitAll()
                .anyRequest().permitAll()
            );
        return http.build();

            
    }
    
}