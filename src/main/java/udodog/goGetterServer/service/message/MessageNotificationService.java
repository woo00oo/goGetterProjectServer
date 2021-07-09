package udodog.goGetterServer.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.message.MessageNotificationRequest;
import udodog.goGetterServer.model.dto.response.message.MessageNotificationResponse;
import udodog.goGetterServer.repository.MessageRepository;


@Service
@RequiredArgsConstructor
public class MessageNotificationService {

    private final MessageRepository messageRepository;

    public DefaultRes<MessageNotificationResponse> messageNotification(MessageNotificationRequest request) {
        Long receiverId = request.getReceiverId();
        Long uncheckedMessageCnt = messageRepository.countUncheckedMessage(receiverId);

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공", new MessageNotificationResponse(uncheckedMessageCnt));
    }
}
