package org.community.post.domain.content;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentContentTest {


    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
        // given
        String content = "This is a comment";

        // when
        CommentContent commentContent = new CommentContent(content);

        // then
        assertEquals(content, commentContent.getContentText());
    }

}