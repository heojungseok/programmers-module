package com.module.boundedContext.post.in;

import com.module.boundedContext.post.app.PostFacade;
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
public class PostDataInit {
    private final PostFacade postFacade;

    @Bean
    @Order(2)
    public ApplicationRunner postDataInitRunner() {
        return args -> {
            makeBasePosts();
            makeBaseComments();
        };
    }

    private void makeBasePosts() {
        postFacade.initPosts("user1", "user2", "user3");
    }

    private void makeBaseComments() {
    }
}
