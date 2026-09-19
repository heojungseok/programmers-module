package com.module.boundedContext.member.app;

import com.module.boundedContext.member.domain.Member;
import com.module.boundedContext.member.out.MemberRepository;
import com.module.boundedContext.post.domain.PostMember;
import com.module.boundedContext.post.out.PostMemberRepository;
import com.module.global.response.ResponseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles("test")
class MemberSyncTest {

    @Autowired
    MemberFacade memberFacade;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostMemberRepository postMemberRepository;

    @Test
    void 회원가입_시_복제본생성() {
        Member member = memberFacade.join("test1", "1234", "테스트").getData();

        PostMember replica = postMemberRepository.findById(member.getId()).get();

        assertThat(replica.getId()).isEqualTo(member.getId());
        assertThat(replica.getUsername()).isEqualTo(member.getUsername());
        assertThat(replica.getNickname()).isEqualTo(member.getNickname());
        assertThat(replica.getActivityScore()).isEqualTo(member.getActivityScore());
        assertThat(replica.getCreatedAt()).isEqualTo(member.getCreatedAt());
        assertThat(replica.getModifiedAt()).isEqualTo(member.getModifiedAt());
    }

    @Test
    void 활동점수_변경_시_복제본_갱신() {
        Member before = memberFacade.join("test2", "1234", "테스트").getData();
        long beforeCount = postMemberRepository.count();

        memberFacade.increaseMemberActivityScore(before.getId(), 3);

        Member after = memberRepository.findById(before.getId()).get();
        PostMember replica = postMemberRepository.findById(before.getId()).get();

        long afterCount = postMemberRepository.count();

        assertThat(after.getActivityScore()).isEqualTo(before.getActivityScore() + 3);
        assertThat(replica.getActivityScore()).isEqualTo(after.getActivityScore());
        assertThat(replica.getModifiedAt()).isEqualTo(after.getModifiedAt());
        assertThat(afterCount).isEqualTo(beforeCount);
    }
}