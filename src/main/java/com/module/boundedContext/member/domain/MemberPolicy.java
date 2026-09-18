package com.module.boundedContext.member.domain;

import org.springframework.stereotype.Service;

@Service
public class MemberPolicy {
    public static final int POST_WRITE_SCORE = 3;
    public static final int COMMENT_WRITE_SCORE = 1;
}
