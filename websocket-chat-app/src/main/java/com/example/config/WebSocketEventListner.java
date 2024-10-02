package com.example.config;

import com.example.modle.ChatMessage;
import com.example.modle.MessageType;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;

import static com.example.Constant.WEB_SOCKET_CHAT_PUBLIC;

@Component
@AllArgsConstructor
public class WebSocketEventListner {

    private final SimpMessagingTemplate messagingTemplate;

    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    )
    {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");
        String dateAndTime = (String) accessor.getSessionAttributes().get("dateAndTime");

        if (username!=null){
            var chatMessage = ChatMessage.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(username)
                    .localDateTime(LocalDateTime.parse(dateAndTime))
                    .build();
           messagingTemplate.convertAndSend(WEB_SOCKET_CHAT_PUBLIC, chatMessage);
        }

    }
}
