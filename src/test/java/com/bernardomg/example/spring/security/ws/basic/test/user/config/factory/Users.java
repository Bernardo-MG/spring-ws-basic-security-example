
package com.bernardomg.example.spring.security.ws.basic.test.user.config.factory;

import java.util.List;

import com.bernardomg.example.spring.security.ws.basic.user.domain.model.User;

public final class Users {

    public static final User disabled() {
        return User.builder()
            .withPrivileges(List.of(Privileges.create()))
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(false)
            .withExpired(false)
            .withPasswordExpired(false)
            .withLocked(false)
            .build();
    }

    public static final User enabled() {
        return User.builder()
            .withPrivileges(List.of(Privileges.create()))
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(true)
            .withExpired(false)
            .withPasswordExpired(false)
            .withLocked(false)
            .build();
    }

    public static final User expired() {
        return User.builder()
            .withPrivileges(List.of(Privileges.create()))
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(true)
            .withExpired(true)
            .withPasswordExpired(false)
            .withLocked(false)
            .build();
    }

    public static final User locked() {
        return User.builder()
            .withPrivileges(List.of(Privileges.create()))
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(true)
            .withExpired(false)
            .withPasswordExpired(false)
            .withLocked(true)
            .build();
    }

    public static final User noPrivileges() {
        return User.builder()
            .withPrivileges(List.of())
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(true)
            .withExpired(false)
            .withPasswordExpired(false)
            .withLocked(false)
            .build();
    }

    public static final User passwordExpired() {
        return User.builder()
            .withPrivileges(List.of(Privileges.create()))
            .withName(UserConstants.NAME)
            .withUsername(UserConstants.USERNAME)
            .withEmail(UserConstants.EMAIL)
            .withEnabled(true)
            .withExpired(false)
            .withPasswordExpired(true)
            .withLocked(false)
            .build();
    }

}
