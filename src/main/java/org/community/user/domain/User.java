package org.community.user.domain;

import org.community.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {
    private final Long id;
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public Long getId() {
        return id;
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
