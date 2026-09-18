package com.module.shared.member.domain;

import com.module.global.jpa.entity.BaseEntity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class ReplicaMember extends BaseMember {

    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ReplicaMember(String username, String nickname, int activityScore, Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(username, nickname, activityScore);
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
