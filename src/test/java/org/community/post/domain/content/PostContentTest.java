package org.community.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCrated_thenReturnTextContent() {
        // given
        String text = "Hello World";

        // when
        PostContent content = new PostContent(text);

        // then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCrated_thenThrowError() {
        // given
        String text = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷀, 닭, 굵, 랅, 꿁, 촗"})
    void givenContentLengthIsOverKorean_whenCrated_thenThrowError(String koreanWord) {
        // given
        String text = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }


    @Test
    void givenContentLengthIsUnder_whenCrated_thenThrowError() {
        // given
        String text = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCrated_thenThrowError(String text) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));

    }

    @Test
    void givenContentLengthIsOk_when_thenNotThrowError() {
        // given
        String text = "this is a test content";
        PostContent content = new PostContent(text);

        // when
        content.updateContent("this is a test content");

        // then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        String text = "this is a test content";
        PostContent content = new PostContent(text);

        // when
        String updateText = "updated content";
        content.updateContent(updateText);

        // then
        assertEquals(content.contentText, updateText);
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        // given
        String text = "this is a test content";
        PostContent content = new PostContent(text);

        // when, then
        String updateText = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷀, 닭, 굵, 랅, 꿁, 촗"})
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord) {
        // given
        String text = "this is a test content";
        PostContent content = new PostContent(text);

        // when, then
        String updateText = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

}