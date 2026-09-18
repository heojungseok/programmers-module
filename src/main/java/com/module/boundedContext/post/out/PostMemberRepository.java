package com.module.boundedContext.post.out;

import com.module.boundedContext.post.domain.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
    Optional<PostMember> findByUsername(String username);
}
