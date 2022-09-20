
package com.bernardomg.example.ws.security.basic.auth.login.service;

import com.bernardomg.example.ws.security.basic.auth.login.model.UserForm;

public interface LoginService {

    public UserForm login(final String username, final String password);

}
