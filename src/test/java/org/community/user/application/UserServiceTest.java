package org.community.user.application;

import org.community.user.application.Interfaces.UserRepository;
import org.community.user.application.dto.CreateUserRequestDto;
import org.community.user.domain.User;
import org.community.user.domain.UserInfo;
import org.community.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);


    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }

}