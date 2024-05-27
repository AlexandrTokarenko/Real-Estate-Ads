package com.example.realestateads.config;

import com.example.realestateads.entity.User;
import com.example.realestateads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findById(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Неправильна електронна адреса або пароль");
        }

        return user.get();

       /* return user.orElseThrow(() ->
                new UsernameNotFoundException("User does not exists"));*/
    }
}
