package com.module.shared.post.event;

import com.module.boundedContext.post.domain.PostComment;
import com.module.shared.post.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCommentCreatedEvent {
    private final PostCommentDto postCommentDto;
}
