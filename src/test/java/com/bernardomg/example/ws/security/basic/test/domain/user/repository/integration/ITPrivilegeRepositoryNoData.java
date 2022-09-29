
package com.bernardomg.example.ws.security.basic.test.domain.user.repository.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.example.ws.security.basic.domain.user.model.Privilege;
import com.bernardomg.example.ws.security.basic.domain.user.repository.PrivilegeRepository;
import com.bernardomg.example.ws.security.basic.test.config.annotation.IntegrationTest;

import liquibase.repackaged.org.apache.commons.collections4.IterableUtils;

@IntegrationTest
@DisplayName("Privilege repository - no data")
public class ITPrivilegeRepositoryNoData {

    @Autowired
    private PrivilegeRepository repository;

    public ITPrivilegeRepositoryNoData() {
        super();
    }

    @Test
    @DisplayName("Returns all the privileges for a user")
    public void testFindForUser_Count() {
        final Iterable<? extends Privilege> result;

        result = repository.findForUser(1L);

        Assertions.assertEquals(0, IterableUtils.size(result));
    }

}
