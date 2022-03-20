
package com.bernardomg.example.ws.security.basic.resource.login.service;

import com.bernardomg.example.ws.security.basic.resource.login.model.UserForm;

public interface LoginService {

    public UserForm login(final String username, final String password);

}
