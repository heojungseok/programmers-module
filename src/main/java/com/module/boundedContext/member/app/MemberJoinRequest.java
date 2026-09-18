package com.module.boundedContext.member.app;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberJoinRequest {
    private final String username;
    private final String password;
    private final String nickname;
}
