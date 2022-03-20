
package com.bernardomg.example.ws.security.basic.resource.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class DefaultLoginService implements LoginService {

    private final UserDetailsService service;

    private final PasswordEncoder    passwordEncoder;

    public DefaultLoginService(final UserDetailsService service,
            final PasswordEncoder passwordEncoder) {
        super();

        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public final Boolean login(final String username, final String password) {
        final UserDetails details;
        final Boolean logged;

        log.debug("Trying to log: {}", username);

        details = service.loadUserByUsername(username);

        if (details == null) {
            logged = false;
        } else {
            logged = passwordEncoder.matches(password, details.getPassword());
        }

        return logged;
    }

}
