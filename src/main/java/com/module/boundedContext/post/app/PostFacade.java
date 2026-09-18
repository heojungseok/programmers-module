package com.module.boundedContext.post.app;

import com.module.boundedContext.post.domain.PostMember;
import com.module.boundedContext.post.out.PostMemberRepository;
import com.module.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostMemberRepository postMemberRepository;

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
}
