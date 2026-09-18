package com.module.boundedContext.post.app;

import com.module.boundedContext.post.domain.Post;
import com.module.boundedContext.post.domain.PostComment;
import com.module.boundedContext.post.domain.PostMember;
import com.module.boundedContext.post.out.PostRepository;
import com.module.global.eventPublisher.EventPublisher;
import com.module.global.response.ResponseData;
import com.module.shared.post.dto.PostCommentDto;
import com.module.shared.post.dto.PostDto;
import com.module.shared.post.event.PostCommentCreatedEvent;
import com.module.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public ResponseData<Post> write(PostMember author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));

        String secureTip = "1234";

        return new ResponseData<>("201-1", "%d번 글이 생성되었습니다. 보안 팁: %s".formatted(post.getId(), secureTip), post);
    }

    public void add(Post post, PostMember author, String comment) {
        PostComment postComment = post.addComment(author, comment);
        postRepository.flush();
        eventPublisher.publish(new PostCommentCreatedEvent(new PostCommentDto(postComment)));
    }
}
