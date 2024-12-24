package org.community.post.domain;

import org.community.post.domain.content.PostContent;
import org.community.post.domain.content.PostPublicationState;
import org.community.user.domain.User;
import org.community.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {
    private final UserInfo userInfo = new UserInfo("test", "url");
    private final User user = new User(1L, userInfo);
    private final User anotherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        post.like(anotherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        // given
        post.like(anotherUser);

        // when
        post.unLike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String newContent = "new content";

        // when
        post.updatePost(user, newContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(newContent, post.getContent());
    }


}