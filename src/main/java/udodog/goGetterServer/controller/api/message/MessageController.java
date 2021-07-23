package udodog.goGetterServer.controller.api.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.entity.Message;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/send")
    public void SendToMessage(Message msg){
        simpMessagingTemplate.convertAndSend("/topic/"+msg.getMessageRoom().getId() , msg);
    }
}
