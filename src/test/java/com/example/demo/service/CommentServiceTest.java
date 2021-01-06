package com.example.demo.service;

import com.example.demo.exceptions.SpringRedditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    @Test
    @DisplayName("Test should Pass when comment do not contains swear words")
    public void shouldNotContainSwearWordsInsideComment(){
        CommentService commentService=new CommentService(null, null, null,
                null, null, null, null);
        assertFalse(commentService.containsSwearWords("This is a comment"));
    }

    @Test
    @DisplayName("Should Throw Exception when Exception Contains Swear Words")
    public void shouldFailWhenCommentContainsSwearWords() {
        CommentService commentService = new CommentService(null, null, null, null, null, null, null);
        SpringRedditException exception = assertThrows(SpringRedditException.class, () -> {
            commentService.containsSwearWords("This is shitty comment");
        });
        assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));
    }

}