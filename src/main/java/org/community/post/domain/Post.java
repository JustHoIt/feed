package org.community.post.domain;

import org.community.common.domain.PositiveIntegerCounter;
import org.community.post.domain.content.PostContent;
import org.community.post.domain.content.PostPublicationState;
import org.community.user.domain.User;

public class Post {
    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;


    public Post(Long id, User author, PostContent content) {

        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.increase();

    }

    public void unLike() {
        this.likeCount.decrease();
    }

    public void updatePost(User user, String updatedContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(updatedContent);
    }
}
