package udodog.goGetterServer.repository.querydsl.message;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import udodog.goGetterServer.model.entity.Message;

public class MessageNotificationExtensionImpl  extends QuerydslRepositorySupport implements MessageNotificationExtension {

    public MessageNotificationExtensionImpl() {
        super(Message.class);
    }

    @Override
    public Long countUncheckedMessage(Long receiverId) {
//        QMessage message = QMessage.message;
//
//        Long uncheckedCnt = from(message)
//                .where(message.receiver.id.eq(receiverId))
//                .where(message.isChecked.isFalse()).fetchCount();

        return null;
    }
}
