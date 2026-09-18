package com.module.boundedContext.member.domain;

import com.module.global.jpa.entity.BaseIdAndTime;
import com.module.shared.member.domain.SourceMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {

    private String password;

    public Member(String username, String password, String nickname) {
        super(username, nickname);
        this.password = password;
    }
}
