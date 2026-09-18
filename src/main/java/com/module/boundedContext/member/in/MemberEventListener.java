package com.module.boundedContext.member.in;

import com.module.boundedContext.member.app.MemberFacade;
import com.module.shared.post.dto.PostDto;
import com.module.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.*;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberFacade memberFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handle(PostCreatedEvent event) {
        memberFacade.increaseMemberActivityScore(event.getPostDto().getAuthorId());
    }
}
