package org.community.user.domain;

import java.util.Objects;

public class User {
    private final Long id;
    private final UserInfo userInfo;
    private final UserRelationCounter followingCount;
    private final UserRelationCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new UserRelationCounter();
        this.followerCounter = new UserRelationCounter();
    }

    //팔로우
    public void follow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();

    }

    //언 팔로우
    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    private void increaseFollowerCount() {
        followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
