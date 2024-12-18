package org.community.post.domain.content;

public class CommentContent extends Content {

    private static final int MIN_COMMENT_LENGTH = 2;
    private static final int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() < MIN_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}