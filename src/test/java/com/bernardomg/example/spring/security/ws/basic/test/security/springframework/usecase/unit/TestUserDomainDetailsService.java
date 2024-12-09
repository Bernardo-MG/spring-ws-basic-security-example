
package com.bernardomg.example.spring.security.ws.basic.test.security.springframework.usecase.unit;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bernardomg.example.spring.security.ws.basic.security.springframework.usecase.UserDomainDetailsService;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.repository.UserRepository;
import com.bernardomg.example.spring.security.ws.basic.test.security.test.config.factory.PermissionConstants;
import com.bernardomg.example.spring.security.ws.basic.test.security.test.config.factory.UserConstants;
import com.bernardomg.example.spring.security.ws.basic.test.security.test.config.factory.Users;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserDomainDetailsService")
class TestUserDomainDetailsService {

    @InjectMocks
    private UserDomainDetailsService service;

    @Mock
    private UserRepository           userRepository;

    public TestUserDomainDetailsService() {
        super();
    }

    @Test
    @DisplayName("When the user is disabled it is returned")
    void testLoadByUsername_Disabled() {
        final UserDetails userDetails;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.disabled()));
        given(userRepository.findPassword(UserConstants.USERNAME)).willReturn(Optional.of(UserConstants.PASSWORD));

        // WHEN
        userDetails = service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userDetails.getUsername())
                .as("username")
                .isEqualTo(UserConstants.USERNAME);
            softly.assertThat(userDetails.getPassword())
                .as("password")
                .isEqualTo(UserConstants.PASSWORD);
            softly.assertThat(userDetails.isAccountNonExpired())
                .as("non expired")
                .isTrue();
            softly.assertThat(userDetails.isAccountNonLocked())
                .as("non locked")
                .isTrue();
            softly.assertThat(userDetails.isCredentialsNonExpired())
                .as("credentials non expired")
                .isTrue();
            softly.assertThat(userDetails.isEnabled())
                .as("enabled")
                .isFalse();

            softly.assertThat(userDetails.getAuthorities())
                .as("authorities size")
                .hasSize(1);
            softly.assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .first()
                .as("authority resource")
                .isEqualTo(PermissionConstants.DATA_CREATE);
        });
    }

    @Test
    @DisplayName("When the user is enabled it is returned")
    void testLoadByUsername_Enabled() {
        final UserDetails userDetails;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.enabled()));
        given(userRepository.findPassword(UserConstants.USERNAME)).willReturn(Optional.of(UserConstants.PASSWORD));

        // WHEN
        userDetails = service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userDetails.getUsername())
                .as("username")
                .isEqualTo(UserConstants.USERNAME);
            softly.assertThat(userDetails.getPassword())
                .as("password")
                .isEqualTo(UserConstants.PASSWORD);
            softly.assertThat(userDetails.isAccountNonExpired())
                .as("non expired")
                .isTrue();
            softly.assertThat(userDetails.isAccountNonLocked())
                .as("non locked")
                .isTrue();
            softly.assertThat(userDetails.isCredentialsNonExpired())
                .as("credentials non expired")
                .isTrue();
            softly.assertThat(userDetails.isEnabled())
                .as("enabled")
                .isTrue();

            softly.assertThat(userDetails.getAuthorities())
                .as("authorities size")
                .hasSize(1);
            softly.assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .first()
                .as("authority resource")
                .isEqualTo(PermissionConstants.DATA_CREATE);
        });
    }

    @Test
    @DisplayName("When the user is expired it is returned")
    void testLoadByUsername_Expired() {
        final UserDetails userDetails;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.expired()));
        given(userRepository.findPassword(UserConstants.USERNAME)).willReturn(Optional.of(UserConstants.PASSWORD));

        // WHEN
        userDetails = service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userDetails.getUsername())
                .as("username")
                .isEqualTo(UserConstants.USERNAME);
            softly.assertThat(userDetails.getPassword())
                .as("password")
                .isEqualTo(UserConstants.PASSWORD);
            softly.assertThat(userDetails.isAccountNonExpired())
                .as("non expired")
                .isFalse();
            softly.assertThat(userDetails.isAccountNonLocked())
                .as("non locked")
                .isTrue();
            softly.assertThat(userDetails.isCredentialsNonExpired())
                .as("credentials non expired")
                .isTrue();
            softly.assertThat(userDetails.isEnabled())
                .as("enabled")
                .isTrue();

            softly.assertThat(userDetails.getAuthorities())
                .as("authorities size")
                .hasSize(1);
            softly.assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .first()
                .as("authority resource")
                .isEqualTo(PermissionConstants.DATA_CREATE);
        });
    }

    @Test
    @DisplayName("When the user is locked it is returned")
    void testLoadByUsername_Locked() {
        final UserDetails userDetails;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.locked()));
        given(userRepository.findPassword(UserConstants.USERNAME)).willReturn(Optional.of(UserConstants.PASSWORD));

        // WHEN
        userDetails = service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userDetails.getUsername())
                .as("username")
                .isEqualTo(UserConstants.USERNAME);
            softly.assertThat(userDetails.getPassword())
                .as("password")
                .isEqualTo(UserConstants.PASSWORD);
            softly.assertThat(userDetails.isAccountNonExpired())
                .as("non expired")
                .isTrue();
            softly.assertThat(userDetails.isAccountNonLocked())
                .as("non locked")
                .isFalse();
            softly.assertThat(userDetails.isCredentialsNonExpired())
                .as("credentials non expired")
                .isTrue();
            softly.assertThat(userDetails.isEnabled())
                .as("enabled")
                .isTrue();

            softly.assertThat(userDetails.getAuthorities())
                .as("authorities size")
                .hasSize(1);
            softly.assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .first()
                .as("authority resource")
                .isEqualTo(PermissionConstants.DATA_CREATE);
        });
    }

    @Test
    @DisplayName("When the user doesn't have authorities an exception is thrown")
    void testLoadByUsername_NoPrivileges() {
        final ThrowingCallable executable;
        final Exception        exception;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.noPrivileges()));

        // WHEN
        executable = () -> service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        exception = Assertions.catchThrowableOfType(UsernameNotFoundException.class, executable);

        Assertions.assertThat(exception.getMessage())
            .isEqualTo("Username " + UserConstants.USERNAME + " has no authorities");
    }

    @Test
    @DisplayName("When the user has the password expired it is returned")
    void testLoadByUsername_PasswordExpired() {
        final UserDetails userDetails;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.of(Users.passwordExpired()));
        given(userRepository.findPassword(UserConstants.USERNAME)).willReturn(Optional.of(UserConstants.PASSWORD));

        // WHEN
        userDetails = service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userDetails.getUsername())
                .as("username")
                .isEqualTo(UserConstants.USERNAME);
            softly.assertThat(userDetails.getPassword())
                .as("password")
                .isEqualTo(UserConstants.PASSWORD);
            softly.assertThat(userDetails.isAccountNonExpired())
                .as("non expired")
                .isTrue();
            softly.assertThat(userDetails.isAccountNonLocked())
                .as("non locked")
                .isTrue();
            softly.assertThat(userDetails.isCredentialsNonExpired())
                .as("credentials non expired")
                .isFalse();
            softly.assertThat(userDetails.isEnabled())
                .as("enabled")
                .isTrue();

            softly.assertThat(userDetails.getAuthorities())
                .as("authorities size")
                .hasSize(1);
            softly.assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .first()
                .as("authority resource")
                .isEqualTo(PermissionConstants.DATA_CREATE);
        });
    }

    @Test
    @DisplayName("When the user doesn't exist an exception is thrown")
    void testLoadByUsername_UserNotExisting() {
        final ThrowingCallable executable;
        final Exception        exception;

        // GIVEN
        given(userRepository.findOne(UserConstants.USERNAME)).willReturn(Optional.empty());

        // WHEN
        executable = () -> service.loadUserByUsername(UserConstants.USERNAME);

        // THEN
        exception = Assertions.catchThrowableOfType(UsernameNotFoundException.class, executable);

        Assertions.assertThat(exception.getMessage())
            .isEqualTo("Username " + UserConstants.USERNAME + " not found in database");
    }

}
