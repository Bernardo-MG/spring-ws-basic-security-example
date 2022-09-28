
package com.bernardomg.example.ws.security.basic.auth.login.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bernardomg.example.ws.security.basic.auth.login.model.ImmutableLoginStatus;
import com.bernardomg.example.ws.security.basic.auth.login.model.LoginStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public final class DefaultLoginService implements LoginService {

    private final PasswordEncoder    passwordEncoder;

    private final UserDetailsService service;

    @Override
    public final LoginStatus login(final String username, final String password) {
        final Boolean         logged;
        Optional<UserDetails> details;

        log.debug("Trying to log: {}", username);

        try {
            details = Optional.of(service.loadUserByUsername(username));
        } catch (final UsernameNotFoundException e) {
            details = Optional.empty();
        }

        if (details.isEmpty()) {
            log.debug("No user for username {}", username);
            logged = false;
        } else {
            logged = passwordEncoder.matches(password, details.get()
                .getPassword());
        }

        return new ImmutableLoginStatus(username, logged);
    }

}
