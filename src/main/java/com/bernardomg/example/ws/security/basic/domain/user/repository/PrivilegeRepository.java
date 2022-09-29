
package com.bernardomg.example.ws.security.basic.domain.user.repository;

import java.util.Collection;

import com.bernardomg.example.ws.security.basic.domain.user.model.Privilege;

public interface PrivilegeRepository {

    public Collection<Privilege> findForUser(final Long id);

}
