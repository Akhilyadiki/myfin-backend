package com.myfin.customerservice.security;

import com.myfin.customerservice.entity.Customer;
import com.myfin.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!c.isActive()) throw new UsernameNotFoundException("User inactive");
        return User.withUsername(c.getUsername())
                .password(c.getPasswordHash())
                .roles("CUSTOMER")
                .build();
    }
}