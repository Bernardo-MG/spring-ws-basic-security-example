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

package com.bernardomg.example.ws.security.basic.test.auth.login.controller.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bernardomg.example.ws.security.basic.test.config.annotation.MvcIntegrationTest;

@MvcIntegrationTest
@DisplayName("Login controller - security")
@Sql({ "/db/queries/user/single.sql", "/db/queries/role/single.sql", "/db/queries/privilege/multiple.sql",
        "/db/queries/relationship/role_privilege.sql", "/db/queries/relationship/user_role.sql" })
public final class ITLoginControllerSecurity {

    @Autowired
    private MockMvc mockMvc;

    public ITLoginControllerSecurity() {
        super();
    }

    @Test
    @DisplayName("Accepts unauthenticated requests")
    public final void testGet_unauthorized() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequest());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isOk());
    }

    private final RequestBuilder getRequest() {
        return MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"username\":\"admin\", \"password\":\"1234\" }");
    }

}
