
package com.bernardomg.example.spring.security.ws.basic.test.user.config.factory;

import com.bernardomg.example.spring.security.ws.basic.user.domain.model.Privilege;

public final class Privileges {

    public static final Privilege create() {
        return new Privilege(PermissionConstants.DATA_CREATE);
    }

}
