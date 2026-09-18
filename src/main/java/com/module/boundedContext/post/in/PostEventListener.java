package com.module.boundedContext.post.in;

import com.module.boundedContext.post.app.PostFacade;
import com.module.shared.member.event.MemberJoinedEvent;
import com.module.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.*;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    private final PostFacade postFacade;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(MemberJoinedEvent event) {
        postFacade.syncMember(event.getMemberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(MemberModifiedEvent event) {
        postFacade.syncMember(event.getMemberDto());
    }
}
