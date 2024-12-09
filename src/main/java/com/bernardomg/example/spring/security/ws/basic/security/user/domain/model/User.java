/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2023 the original author or authors.
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

package com.bernardomg.example.spring.security.ws.basic.security.user.domain.model;

import java.util.Collection;
import java.util.Objects;

import lombok.Builder;

/**
 * Representation of a user.
 * <p>
 * FIXME: this should be immutable
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Builder(setterPrefix = "with")
public record User(String email, boolean enabled, boolean expired, boolean locked, String name, boolean passwordExpired,
        Collection<Privilege> privileges, String username) {

    public User(final String email, final boolean enabled, final boolean expired, final boolean locked,
            final String name, final boolean passwordExpired, final Collection<Privilege> privileges,
            final String username) {
        if (Objects.nonNull(name)) {
            this.name = name.trim();
        } else {
            this.name = null;
        }

        if (Objects.nonNull(username)) {
            this.username = username.trim()
                .toLowerCase();
        } else {
            this.username = null;
        }

        if (Objects.nonNull(email)) {
            this.email = email.trim()
                .toLowerCase();
        } else {
            this.email = null;
        }

        this.enabled = enabled;
        this.expired = expired;
        this.locked = locked;
        this.passwordExpired = passwordExpired;
        this.privileges = privileges;
    }

}