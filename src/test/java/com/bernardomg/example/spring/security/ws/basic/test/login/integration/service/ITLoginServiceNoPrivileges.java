
package com.bernardomg.example.spring.security.ws.basic.test.login.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.example.spring.security.ws.basic.login.model.LoginStatus;
import com.bernardomg.example.spring.security.ws.basic.login.service.LoginService;
import com.bernardomg.example.spring.security.ws.basic.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Login service - no privileges")
@Sql({ "/db/queries/user/single.sql", "/db/queries/security/default_role_no_privileges.sql" })
public class ITLoginServiceNoPrivileges {

    @Autowired
    private LoginService service;

    public ITLoginServiceNoPrivileges() {
        super();
    }

    @Test
    @DisplayName("An existing user can't log in")
    public final void testLogin_valid() {
        final LoginStatus result;

        result = service.login("admin", "1234");

        Assertions.assertFalse(result.getLogged());
    }

}
