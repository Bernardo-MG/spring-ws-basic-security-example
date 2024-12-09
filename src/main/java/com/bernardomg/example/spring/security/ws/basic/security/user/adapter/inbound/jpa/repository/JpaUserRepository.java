
package com.bernardomg.example.spring.security.ws.basic.security.user.adapter.inbound.jpa.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bernardomg.example.spring.security.ws.basic.security.user.adapter.inbound.jpa.model.PrivilegeEntity;
import com.bernardomg.example.spring.security.ws.basic.security.user.adapter.inbound.jpa.model.RoleEntity;
import com.bernardomg.example.spring.security.ws.basic.security.user.adapter.inbound.jpa.model.UserEntity;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.model.Privilege;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.repository.UserRepository;

@Repository
public final class JpaUserRepository implements UserRepository {

    private final UserSpringRepository userSpringRepository;

    public JpaUserRepository(final UserSpringRepository userSpringRepo) {
        super();

        userSpringRepository = Objects.requireNonNull(userSpringRepo, "Received a null pointer as user repository");
    }

    @Override
    public Optional<User> findOne(final String username) {
        return userSpringRepository.findOneByUsername(username)
            .map(this::toDomain);
    }

    @Override
    public Optional<String> findPassword(final String username) {
        return userSpringRepository.findOneByUsername(username)
            .map(UserEntity::getPassword);
    }

    private Privilege toDomain(final PrivilegeEntity entity) {
        return new Privilege(entity.getName());
    }

    private User toDomain(final UserEntity entity) {
        final Collection<Privilege> privileges;

        privileges = entity.getRoles()
            .stream()
            .map(RoleEntity::getPrivileges)
            .flatMap(Collection::stream)
            .map(this::toDomain)
            .toList();
        return User.builder()
            .withName(entity.getName())
            .withEmail(entity.getEmail())
            .withUsername(entity.getUsername())
            .withEnabled(entity.getEnabled())
            .withExpired(entity.getExpired())
            .withLocked(entity.getLocked())
            .withPasswordExpired(entity.getCredentialsExpired())
            .withPrivileges(privileges)
            .build();
    }

}
