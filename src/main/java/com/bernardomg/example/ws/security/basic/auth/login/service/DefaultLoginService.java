
package com.bernardomg.example.ws.security.basic.auth.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bernardomg.example.ws.security.basic.auth.login.model.UserForm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public final class DefaultLoginService implements LoginService {

    private final PasswordEncoder    passwordEncoder;

    private final UserDetailsService service;

    @Override
    public final UserForm login(final String username, final String password) {
        final UserDetails details;
        final Boolean     logged;
        final UserForm    user;

        log.debug("Trying to log: {}", username);

        details = service.loadUserByUsername(username);

        if (details == null) {
            logged = false;
        } else {
            logged = passwordEncoder.matches(password, details.getPassword());
        }

        if (logged) {
            user = toUser(details);
        } else {
            user = null;
        }

        return user;
    }

    private final UserForm toUser(final UserDetails details) {
        final UserForm user;

        user = new UserForm();
        user.setUsername(details.getUsername());

        return user;
    }

}
