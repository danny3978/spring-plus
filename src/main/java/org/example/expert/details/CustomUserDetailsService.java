package org.example.expert.details;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.exception.NotFoundException;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
               new UsernameNotFoundException(email + " not found")
        );

        if (user != null) {
            return new CustomUserDetails(user);
        }

        return null;
    }
}
