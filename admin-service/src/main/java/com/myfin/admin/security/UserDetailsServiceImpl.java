package com.myfin.admin.security;

import com.myfin.admin.entity.Admin;
import com.myfin.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin a = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return User.withUsername(a.getUsername())
                .password(a.getPasswordHash())
                .roles("ADMIN")
                .build();
    }
}