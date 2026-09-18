package com.module.boundedContext.post.out;

import com.module.boundedContext.post.domain.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
}
