package com.module.shared.post.event;

import com.module.shared.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCreatedEvent {
    private final PostDto postDto;
}
