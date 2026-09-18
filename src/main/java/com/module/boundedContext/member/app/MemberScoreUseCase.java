package com.module.boundedContext.member.app;

import com.module.boundedContext.member.domain.Member;
import com.module.boundedContext.member.out.MemberRepository;
import com.module.global.eventPublisher.EventPublisher;
import com.module.shared.member.dto.MemberDto;
import com.module.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberScoreUseCase {

    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;

    public void increaseActivityScore(Long authorId, int amount) {
        Member member = memberRepository.findById(authorId).get();
        member.increaseActivityScore(amount);
        memberRepository.flush();
        eventPublisher.publish(new MemberModifiedEvent(new MemberDto(member)));
    }
}
