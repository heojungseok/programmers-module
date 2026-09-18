package com.module.boundedContext.member.in;

import com.module.boundedContext.member.app.MemberFacade;
import com.module.boundedContext.member.app.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MemberDataInit {

    private final MemberFacade memberFacade;

    @Bean
    @Order(1)
    public ApplicationRunner memberDataInitRunner() {
        return args -> {
            makeBaseMembers();
        };
    }

    private void makeBaseMembers() {
        List<MemberJoinRequest> members = createInitMembers();
        memberFacade.joinBaseMembersIfEmpty(members);
    }

    private List<MemberJoinRequest> createInitMembers() {
        return List.of(
                new MemberJoinRequest("system", "1234", "시스템"),
                new MemberJoinRequest("holding", "1234", "홀딩"),
                new MemberJoinRequest("admin", "1234", "관리자"),
                new MemberJoinRequest("user1", "1234", "유저1"),
                new MemberJoinRequest("user2", "1234", "유저2"),
                new MemberJoinRequest("user3", "1234", "유저3")
        );
    }

}
