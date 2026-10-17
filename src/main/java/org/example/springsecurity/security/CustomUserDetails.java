package org.example.springsecurity.security;
import org.example.springsecurity.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;
public class CustomUserDetails implements UserDetails {
 private final Customer customer;
 private final Set<GrantedAuthority> authorities;
 public CustomUserDetails(Customer customer) {
  this.customer=customer;
  Set<GrantedAuthority> a=new HashSet<>();
  if(customer.getRoles()!=null) for(var role:customer.getRoles()) {
   a.add(new SimpleGrantedAuthority("ROLE_"+role.getName().replace("ROLE_","")));
   if(role.getPermissions()!=null) role.getPermissions().forEach(p -> a.add(new SimpleGrantedAuthority(p.getName())));
  }
  this.authorities=a;
 }
 public Long getId(){return customer.getId();}
 public String getFullName(){return customer.getFullName();}
 public Set<String> getRoleNames(){return customer.getRoles().stream().map(r->r.getName()).collect(Collectors.toSet());}
 @Override public Collection<? extends GrantedAuthority> getAuthorities(){return authorities;}
 @Override public String getPassword(){return customer.getPassword();}
 @Override public String getUsername(){return customer.getEmail();}
 @Override public boolean isEnabled(){return customer.isEnabled();}
 @Override public boolean isAccountNonLocked(){return customer.isAccountNonLocked();}
 @Override public boolean isAccountNonExpired(){return true;}
 @Override public boolean isCredentialsNonExpired(){return true;}
}
