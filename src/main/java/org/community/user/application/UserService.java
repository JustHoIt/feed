package org.community.user.application;

import org.community.user.application.Interfaces.UserRepository;
import org.community.user.application.dto.CreateUserRequestDto;
import org.community.user.domain.User;
import org.community.user.domain.UserInfo;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);

        return userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
