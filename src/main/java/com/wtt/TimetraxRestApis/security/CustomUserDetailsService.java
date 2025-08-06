package com.wtt.TimetraxRestApis.security;


import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ResourceRepo resourceRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Resource resource = resourceRepo.findByEmailId(emailId);
        
        if (resource == null) {
            throw new UsernameNotFoundException("User not found with email: " + emailId);
        }

        // Check if user is active
        if (!resource.getActive()) {
            throw new UsernameNotFoundException("User account is inactive: " + emailId);
        }

        // Create authorities based on role
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // Map role ID to role name
        if (resource.getRole() == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (resource.getRole() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Default role
        }

        return new User(
            resource.getEmailId(),
            resource.getPassword(),
            resource.getActive(),
            true, // accountNonExpired
            true, // credentialsNonExpired
            true, // accountNonLocked
            authorities
        );
    }

    /**
     * Load user by resource ID
     */
    public UserDetails loadUserByResourceId(Integer resourceId) throws UsernameNotFoundException {
        Resource resource = resourceRepo.findById(resourceId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + resourceId));
        
        return loadUserByUsername(resource.getEmailId());
    }
}
