
package com.bernardomg.example.spring.security.ws.basic.test.security.test.config.factory;

import com.bernardomg.example.spring.security.ws.basic.security.user.domain.model.Privilege;

public final class Privileges {

    public static final Privilege create() {
        return new Privilege(PermissionConstants.DATA_CREATE);
    }

}
