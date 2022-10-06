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

package com.bernardomg.example.ws.security.basic.auth.basic.token;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.bernardomg.example.ws.security.basic.auth.token.TokenProvider;

/**
 * Basic HTTP token processor.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class BasicTokenProvider implements TokenProvider {

    /**
     * Default constructor.
     */
    public BasicTokenProvider() {
        super();
    }

    @Override
    public final String generateToken(final String username, final String password) {
        final String rawToken;

        rawToken = String.format("%s:%s", username, password);
        return Base64.getEncoder()
            .encodeToString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

}
