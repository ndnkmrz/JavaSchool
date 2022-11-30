package com.gamershop.admin.security;

import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GamershopUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;
    public GamershopUserDetailsService(UserRepository userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.getUserEntityByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with email: " + email));
        return new GamershopUserDetails(user);
    }
}
