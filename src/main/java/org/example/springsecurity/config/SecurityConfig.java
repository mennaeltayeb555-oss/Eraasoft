package org.example.springsecurity.config;
import org.example.springsecurity.security.*;
import org.example.springsecurity.security.BankAuthenticationProvider;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean AuthenticationManager authenticationManager(BankAuthenticationProvider provider){return new ProviderManager(provider);}
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http, BankAuthenticationProvider provider,
   org.springframework.security.web.AuthenticationEntryPoint entryPoint,
   org.springframework.security.web.access.AccessDeniedHandler deniedHandler) throws Exception {
  http.csrf(csrf->csrf.disable())
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authenticationProvider(provider)
   .httpBasic(org.springframework.security.config.Customizer.withDefaults())
   .exceptionHandling(e->e.authenticationEntryPoint(entryPoint).accessDeniedHandler(deniedHandler))
   .authorizeHttpRequests(auth->auth
    .requestMatchers(HttpMethod.POST,"/auth/register","/auth/login").permitAll()
    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
    .requestMatchers("/accounts/**","/transfers/**","/customers/**","/reports/**","/auth/me").authenticated()
    .anyRequest().denyAll());
  return http.build();
 }
 @Bean org.springframework.security.web.AuthenticationEntryPoint jsonAuthenticationEntryPoint(){
  return (request,response,exception)->{
   response.setStatus(401); response.setContentType("application/json");
   response.getWriter().write("{\"timestamp\":\""+java.time.Instant.now()+"\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Authentication required\",\"path\":\""+request.getRequestURI()+"\"}");
  };
 }
 @Bean org.springframework.security.web.access.AccessDeniedHandler jsonAccessDeniedHandler(){
  return (request,response,exception)->{
   response.setStatus(403); response.setContentType("application/json");
   response.getWriter().write("{\"timestamp\":\""+java.time.Instant.now()+"\",\"status\":403,\"error\":\"Forbidden\",\"message\":\"Access denied\",\"path\":\""+request.getRequestURI()+"\"}");
  };
 }
}
