/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022-2023 the original author or authors.
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

package com.bernardomg.example.spring.security.ws.basic.test.person.adapter.outbound.rest.controller.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bernardomg.example.spring.security.ws.basic.test.config.annotation.MvcIntegrationTest;
import com.bernardomg.example.spring.security.ws.basic.test.security.user.config.CredentialsExpiredUser;
import com.bernardomg.example.spring.security.ws.basic.test.security.user.config.DisabledUser;
import com.bernardomg.example.spring.security.ws.basic.test.security.user.config.LockedUser;
import com.bernardomg.example.spring.security.ws.basic.test.security.user.config.ValidUser;

@MvcIntegrationTest
@DisplayName("Example entity controller - security")
final class ITPersonControllerSecurity {

    private static final String ROUTE = "/person";

    @Autowired
    private MockMvc             mockMvc;

    private final RequestBuilder getRequest() {
        return MockMvcRequestBuilders.get(ROUTE);
    }

    private final RequestBuilder getRequestAuthorized() {
        return MockMvcRequestBuilders.get(ROUTE)
            .header("Authorization", "Basic YWRtaW46MTIzNA==");
    }

    @Test
    @DisplayName("An authenticated request is authorized")
    @ValidUser
    void testGet_authorized() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequestAuthorized());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isOk());
    }

    @Test
    @DisplayName("A locked user is not authorized")
    @CredentialsExpiredUser
    void testGet_credentialsExpired() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequestAuthorized());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isUnauthorized());
    }

    @Test
    @DisplayName("An expired user is not authorized")
    @DisabledUser
    void testGet_expired() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequestAuthorized());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isUnauthorized());
    }

    @Test
    @DisplayName("A locked user is not authorized")
    @LockedUser
    void testGet_locked() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequestAuthorized());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isUnauthorized());
    }

    @Test
    @DisplayName("A disabled user is not authorized")
    @DisabledUser
    void testGet_notAuthorized() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequestAuthorized());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isUnauthorized());
    }

    @Test
    @DisplayName("A not authenticated request is not authorized")
    @ValidUser
    void testGet_unauthorized() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getRequest());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status()
            .isUnauthorized());
    }

}
