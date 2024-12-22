package org.community.user.application;

import org.community.user.application.Interfaces.UserRelationRepository;
import org.community.user.application.dto.FollowUserRequestDto;
import org.community.user.domain.User;

public class UserRelationService {
    private static UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
                               UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unFollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }


}
