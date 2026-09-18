package com.module.shared.post.dto;

import com.module.boundedContext.post.domain.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCommentDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long postId;
    private final Long authorId;
    private final String authorName;
    private final String content;

    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreatedAt(),
                postComment.getModifiedAt(),
                postComment.getPost().getId(),
                postComment.getAuthor().getId(),
                postComment.getAuthor().getNickname(),
                postComment.getContent()
        );
    }
}
