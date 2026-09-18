package com.module.boundedContext.post.domain;

import com.module.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "POST_POST")
@NoArgsConstructor
@Getter
public class Post extends BaseIdAndTime {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private PostMember author;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public Post(PostMember author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public PostComment addComment(PostMember author, String comment) {
        PostComment postComment = new PostComment(this, author, comment);

        comments.add(postComment);

        return postComment;
    }
}
