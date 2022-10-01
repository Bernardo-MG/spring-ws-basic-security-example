
package com.bernardomg.example.ws.security.basic.test.auth.login.service.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.example.ws.security.basic.auth.login.model.LoginStatus;
import com.bernardomg.example.ws.security.basic.auth.login.service.LoginService;
import com.bernardomg.example.ws.security.basic.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Login service - no data")
public class ITLoginServiceNoData {

    @Autowired
    private LoginService service;

    public ITLoginServiceNoData() {
        super();
    }

    @Test
    @DisplayName("Trying to log in returns a user which isn't logged in")
    public final void testLogin_invalidPassword() throws Exception {
        final LoginStatus result;

        result = service.login("admin", "abc");

        Assertions.assertFalse(result.getLogged());
    }

}