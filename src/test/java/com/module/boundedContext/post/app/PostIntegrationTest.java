package com.module.boundedContext.post.app;

import com.module.boundedContext.member.out.MemberRepository;
import com.module.boundedContext.post.domain.Post;
import com.module.boundedContext.post.domain.PostMember;
import com.module.boundedContext.post.out.PostMemberRepository;
import com.module.boundedContext.post.out.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles("test")
class PostIntegrationTest {

    @Autowired
    PostFacade postFacade;
    @Autowired
    PostMemberRepository postMemberRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    void 글_작성하면_작성자의_활동점수가_3증가() {
        PostMember author = postMemberRepository.findByUsername("user1").get();
        int beforeScore = memberRepository.findById(author.getId()).get().getActivityScore();

        postFacade.write(author, "title", "content");

        int afterScore = memberRepository.findById(author.getId()).get().getActivityScore();

        assertThat(afterScore).isEqualTo(beforeScore + 3);
    }

    @Test
    void 댓글_작성하면_작성자의_활동점수가_1증가() {
        PostMember author = postMemberRepository.findByUsername("user1").get();
        Post post = postRepository.findById(1L).get();
        int beforeScore = memberRepository.findById(author.getId()).get().getActivityScore();

        postFacade.add(post.getId(), author.getId(), "comment");

        int afterScore = memberRepository.findById(author.getId()).get().getActivityScore();
        assertThat(afterScore).isEqualTo(beforeScore + 1);
    }

    @Test
    void 작성중_롤백되면_점수반영_없음() {
        PostMember author = postMemberRepository.findByUsername("user1").get();
        int beforeScore = memberRepository.findById(author.getId()).get().getActivityScore();
        long beforeCount = postRepository.count();

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.executeWithoutResult(status -> {
            postFacade.write(author, "title", "content");
            status.setRollbackOnly();
        });

        int afterScore = memberRepository.findById(author.getId()).get().getActivityScore();
        long afterCount = postRepository.count();

        assertThat(afterScore).isEqualTo(beforeScore);
        assertThat(afterCount).isEqualTo(beforeCount);
    }

}