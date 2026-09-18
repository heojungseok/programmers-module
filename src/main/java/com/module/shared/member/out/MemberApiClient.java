package com.module.shared.member.out;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MemberApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8888/api/members")
            .build();

    public String getRandomSecureTip() {
        return restClient.get()
                .uri("/security-tip")
                .retrieve()
                .body(String.class);
    }
}
