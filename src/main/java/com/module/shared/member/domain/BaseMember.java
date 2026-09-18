package com.module.shared.member.domain;

import com.module.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseMember extends BaseEntity {

    @Column(unique = true)
    private String username;
    private String nickname;
    private int activityScore;

    public BaseMember(String username, String nickname, int activityScore) {
        this.username = username;
        this.nickname = nickname;
        this.activityScore = activityScore;
    }

    protected int increaseActivityScore(int amount) {
        return activityScore += amount;
    }
}
