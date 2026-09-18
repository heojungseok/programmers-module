package com.module.shared.post.dto;

import com.module.boundedContext.post.domain.Post;
import com.module.boundedContext.post.domain.PostMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostDto {

    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long authorId;
    private final String authorName;
    private final String title;
    private final String content;

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getCreatedAt(),
                post.getModifiedAt(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername(),
                post.getTitle(),
                post.getContent()
        );
    }
}
