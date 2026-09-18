package com.module.global.jpa.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    public abstract Long getId();

    public abstract LocalDateTime getCreatedAt();

    public abstract LocalDateTime getModifiedAt();
/*
    protected void publishEvent(Object event) {

    }
    */
}
