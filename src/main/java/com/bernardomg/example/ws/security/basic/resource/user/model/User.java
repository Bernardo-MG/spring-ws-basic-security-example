/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017-2020 the original author or authors.
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

package com.bernardomg.example.ws.security.basic.resource.user.model;

/**
 * User, and all its authentication data.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface User {

    /**
     * Returns the credentials expired flag.
     * <p>
     * This usually means that the password is no longer valid.
     * 
     * @return the credentials expired flag
     */
    public Boolean getCredentialsExpired();

    /**
     * Returns the user email.
     * 
     * @return the user email
     */
    public String getEmail();

    /**
     * Returns the user enabled flag.
     * 
     * @return the user enabled flag
     */
    public Boolean getEnabled();

    /**
     * Returns the user expired flag.
     * <p>
     * This means the user is no longer valid.
     * 
     * @return the user expired flag
     */
    public Boolean getExpired();

    /**
     * Returns the user locked flag.
     * 
     * @return the user locked flag
     */
    public Boolean getLocked();

    /**
     * Returns the user password.
     * 
     * @return the user password
     */
    public String getPassword();

    /**
     * Returns the user roles.
     * 
     * @return the user roles
     */
    public Iterable<? extends Role> getRoles();

    /**
     * Returns the user username.
     * 
     * @return the user username
     */
    public String getUsername();

    /**
     * Sets the credentials expired flag.
     * 
     * @param flag
     *            the credentials expired flag
     */
    public void setCredentialsExpired(final Boolean flag);

    /**
     * Sets the user email.
     * 
     * @param mail
     *            email to set
     */
    public void setEmail(final String mail);

    /**
     * Sets the user enabled flag.
     * 
     * @param flag
     *            the user enabled flag
     */
    public void setEnabled(final Boolean flag);

    /**
     * Sets the user expired flag.
     * 
     * @param flag
     *            the user expired flag
     */
    public void setExpired(final Boolean flag);

    /**
     * Sets the user id.
     * 
     * @param identifier
     *            the new id
     */
    public void setId(final Long identifier);

    /**
     * Sets the user locked flag.
     * 
     * @param flag
     *            the user locked flag
     */
    public void setLocked(final Boolean flag);

    /**
     * Sets the user password.
     * 
     * @param pass
     *            the user password
     */
    public void setPassword(final String pass);

    /**
     * Sets the user name.
     * 
     * @param name
     *            the user name
     */
    public void setUsername(final String name);

}
