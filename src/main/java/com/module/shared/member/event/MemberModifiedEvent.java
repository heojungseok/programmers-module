package com.module.shared.member.event;

import com.module.shared.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberModifiedEvent {
    private final MemberDto memberDto;
}
