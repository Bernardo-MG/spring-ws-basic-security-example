
package com.bernardomg.example.spring.security.ws.basic.test.login.usecase.service.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.example.spring.security.ws.basic.login.domain.model.LoginStatus;
import com.bernardomg.example.spring.security.ws.basic.login.usecase.service.LoginService;
import com.bernardomg.example.spring.security.ws.basic.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Login service")
@Sql({ "/db/queries/user/single.sql", "/db/queries/security/default_role.sql" })
public class ITLoginService {

    @Autowired
    private LoginService service;

    public ITLoginService() {
        super();
    }

    @Test
    @DisplayName("An existing user with invalid password doesn't log in")
    public final void testLogin_invalidPassword() {
        final LoginStatus result;

        result = service.login("admin", "abc");

        Assertions.assertFalse(result.logged());
    }

    @Test
    @DisplayName("A not existing user doesn't log in")
    public final void testLogin_notExisting() {
        final LoginStatus result;

        result = service.login("abc", "1234");

        Assertions.assertFalse(result.logged());
    }

    @Test
    @DisplayName("An existing user with valid password logs in")
    public final void testLogin_valid() {
        final LoginStatus result;

        result = service.login("admin", "1234");

        Assertions.assertTrue(result.logged());
    }

    @Test
    @DisplayName("A valid login returns all the data")
    public final void testLogin_valid_data() {
        final LoginStatus result;

        result = service.login("admin", "1234");

        Assertions.assertEquals("admin", result.username());
        Assertions.assertEquals("YWRtaW46MTIzNA==", result.token());
    }

}
