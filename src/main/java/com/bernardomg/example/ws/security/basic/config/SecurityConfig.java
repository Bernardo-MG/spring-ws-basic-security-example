/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.ws.security.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bernardomg.example.ws.security.basic.security.basic.token.BasicTokenProvider;
import com.bernardomg.example.ws.security.basic.security.login.service.LoginService;
import com.bernardomg.example.ws.security.basic.security.login.service.TokenLoginService;
import com.bernardomg.example.ws.security.basic.security.login.validation.CredentialsLoginValidator;
import com.bernardomg.example.ws.security.basic.security.login.validation.LoginValidator;
import com.bernardomg.example.ws.security.basic.security.token.TokenProvider;
import com.bernardomg.example.ws.security.basic.security.user.repository.PrivilegeRepository;
import com.bernardomg.example.ws.security.basic.security.user.repository.UserRepository;
import com.bernardomg.example.ws.security.basic.security.userdetails.PersistentUserDetailsService;

/**
 * Security configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    /**
     * Default constructor.
     */
    public SecurityConfig() {
        super();
    }

    @Bean("loginService")
    public LoginService getLoginService(final UserDetailsService userDetailsService,
            final PasswordEncoder passwordEncoder, final TokenProvider tokenProv) {
        final LoginValidator loginValidator;

        loginValidator = new CredentialsLoginValidator(userDetailsService, passwordEncoder);
        return new TokenLoginService(tokenProv, loginValidator);
    }

    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("tokenProvider")
    public TokenProvider getTokenProvider() {
        return new BasicTokenProvider();
    }

    @Bean("userDetailsService")
    public UserDetailsService getUserDetailsService(final UserRepository userRepository,
            final PrivilegeRepository privilegeRepository) {
        return new PersistentUserDetailsService(userRepository, privilegeRepository);
    }

}
