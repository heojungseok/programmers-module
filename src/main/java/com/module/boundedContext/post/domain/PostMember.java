package com.module.boundedContext.post.domain;

import com.module.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
    public PostMember(String username, String nickname, int activityScore, Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(username, nickname, activityScore, id, createdAt, modifiedAt);
    }
}
