package com.leave.manager.configuration;

import com.leave.manager.entity.Users;
import com.leave.manager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LMUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users user = usersRepository.loadUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRolesList().stream().toArray(String[]::new))
                .build();

        return userDetails;
    }
}
