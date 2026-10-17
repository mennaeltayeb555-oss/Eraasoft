package org.example.springsecurity.security;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class BankAuthenticationProvider implements AuthenticationProvider {
 private final CustomUserDetailsService userDetailsService;
 private final PasswordEncoder passwordEncoder;
 public BankAuthenticationProvider(CustomUserDetailsService s, PasswordEncoder p){this.userDetailsService=s;this.passwordEncoder=p;}
 @Override public Authentication authenticate(Authentication authentication) {
  String email=String.valueOf(authentication.getPrincipal());
  String raw=String.valueOf(authentication.getCredentials());
  UserDetails user;
  try { user=userDetailsService.loadUserByUsername(email); }
  catch(UsernameNotFoundException ex){throw new BadCredentialsException("Invalid email or password");}
  if(!user.isEnabled()) throw new DisabledException("Customer is disabled");
  if(!user.isAccountNonLocked()) throw new LockedException("Customer account is blocked");
  if(user.getAuthorities().stream().noneMatch(a->a.getAuthority().startsWith("ROLE_"))) throw new BadCredentialsException("Customer has no valid role");
  if(!passwordEncoder.matches(raw,user.getPassword())) throw new BadCredentialsException("Invalid email or password");
  return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
 }
 @Override public boolean supports(Class<?> authentication){return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);}
}
