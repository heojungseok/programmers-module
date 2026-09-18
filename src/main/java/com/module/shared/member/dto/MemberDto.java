package com.module.shared.member.dto;

import com.module.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MemberDto {

    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;
    private final String nickname;
    private final int activityScore;

    public MemberDto(Member member) {
        this(
                member.getId(),
                member.getCreatedAt(),
                member.getModifiedAt(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
