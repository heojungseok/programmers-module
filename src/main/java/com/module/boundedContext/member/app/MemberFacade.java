package com.module.boundedContext.member.app;

import com.module.boundedContext.member.domain.Member;
import com.module.boundedContext.member.out.MemberRepository;
import com.module.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberJoinUseCase memberJoinUseCase;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    @Transactional(readOnly = true)
    public long count() {
        return memberRepository.count();
    }

    @Transactional
    public void joinBaseMembersIfEmpty(List<MemberJoinRequest> members) {
        if (memberRepository.count() > 0) return;

        for (MemberJoinRequest member : members) {
            memberJoinUseCase.join(member.getUsername(), member.getPassword(), member.getNickname());
        }
    }
}
