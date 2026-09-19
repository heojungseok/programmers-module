package com.module.boundedContext.post.app;

import com.module.boundedContext.post.domain.Post;
import com.module.boundedContext.post.domain.PostComment;
import com.module.boundedContext.post.domain.PostMember;
import com.module.boundedContext.post.out.PostMemberRepository;
import com.module.boundedContext.post.out.PostRepository;
import com.module.global.response.ResponseData;
import com.module.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostMemberRepository postMemberRepository;
    private final PostWriteUseCase postWriteUseCase;
    private final PostRepository postRepository;

    @Transactional(propagation = REQUIRES_NEW)
    public void syncMember(MemberDto member) {
        PostMember postMember = new PostMember(
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore(),
                member.getId(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );

        postMemberRepository.save(postMember);
    }

    @Transactional
    public ResponseData<Post> write(PostMember author, String title, String content) {
        return postWriteUseCase.write(author, title, content);
    }

    @Transactional
    public void add(Long postId, Long authorId, String comment) {
        Post post = postRepository.findById(postId).get();
        PostMember author = postMemberRepository.findById(authorId).get();
        postWriteUseCase.add(post, author, comment);
    }

    @Transactional(readOnly = true)
    public Long count() {
        return postRepository.count();
    }

    @Transactional
    public void initPosts(String user1, String user2, String user3) {
        if (postRepository.count() > 0) return;

        PostMember postMember1 = postMemberRepository.findByUsername(user1).get();
        PostMember postMember2 = postMemberRepository.findByUsername(user2).get();
        PostMember postMember3 = postMemberRepository.findByUsername(user3).get();

        ResponseData<Post> response1 = postWriteUseCase.write(postMember1, "제목1", "내용1");
        ResponseData<Post> response2 = postWriteUseCase.write(postMember1, "제목2", "내용2");
        ResponseData<Post> response3 = postWriteUseCase.write(postMember1, "제목3", "내용3");
        ResponseData<Post> response4 = postWriteUseCase.write(postMember2, "제목4", "내용4");
        ResponseData<Post> response5 = postWriteUseCase.write(postMember2, "제목5", "내용5");
        ResponseData<Post> response6 = postWriteUseCase.write(postMember3, "제목6", "내용6");

        log.debug(
                "초기 글 작성 결과: {}, {}, {}, {}, {}, {}",
                response1.getMsg(), response2.getMsg(), response3.getMsg(),
                response4.getMsg(), response5.getMsg(), response6.getMsg()
        );
    }

    @Transactional
    public void initPostComments(long first, long second, long third, long fourth, long fifth, long sixth) {
        Post post1 = postRepository.findById(first).get();
        Post post2 = postRepository.findById(second).get();
        Post post3 = postRepository.findById(third).get();
        Post post4 = postRepository.findById(fourth).get();
        Post post5 = postRepository.findById(fifth).get();
        Post post6 = postRepository.findById(sixth).get();

        PostMember postMember1 = postMemberRepository.findByUsername("user1").get();
        PostMember postMember2 = postMemberRepository.findByUsername("user2").get();
        PostMember postMember3 = postMemberRepository.findByUsername("user3").get();

        if (post1.hasComments()) return;

        postWriteUseCase.add(post1, postMember1, "댓글");
        postWriteUseCase.add(post1, postMember2, "댓글");
        postWriteUseCase.add(post4, postMember3, "댓글");
        postWriteUseCase.add(post2, postMember1, "댓글");
        postWriteUseCase.add(post3, postMember2, "댓글");
        postWriteUseCase.add(post3, postMember2, "댓글");
        postWriteUseCase.add(post6, postMember3, "댓글");
        postWriteUseCase.add(post5, postMember3, "댓글");

    }
}
