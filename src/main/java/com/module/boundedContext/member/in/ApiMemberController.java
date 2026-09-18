package com.module.boundedContext.member.in;

import com.module.boundedContext.member.app.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ApiMemberController {
    private final MemberFacade memberFacade;

    @GetMapping("/security-tip")
    public String getRandomSecureTip() {
        return memberFacade.getRandomSecureTip();
    }
}
