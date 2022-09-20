
package com.bernardomg.example.ws.security.basic.domain.user.service;

import com.bernardomg.example.ws.security.basic.domain.user.model.User;

public interface UserService {

    public Iterable<? extends User> getUsers();

}
