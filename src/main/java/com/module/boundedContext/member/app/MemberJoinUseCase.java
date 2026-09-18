package com.module.boundedContext.member.app;

import com.module.boundedContext.member.domain.Member;
import com.module.boundedContext.member.out.MemberRepository;
import com.module.global.exception.DomainException;
import com.module.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJoinUseCase {

    private final MemberRepository memberRepository;

    public ResponseData<Member> join(String username, String password, String nickname) {
        memberRepository.findByUsername(username).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });

        Member member = memberRepository.save(new Member(username, password, nickname));

        return new ResponseData<>("201-1", "%d번 회원이 생성되었습니다.".formatted(member.getId()), member);
    }
}
