package org.exam.java.project.final_project.security;

import java.util.Optional;

import org.exam.java.project.final_project.model.User;
import org.exam.java.project.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);
        if(userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new DatabaseUserDetails(userAttempt.get());
    }


    

}

