package com.example.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    public String content;
    public String sender;
    public MessageType messageType;
    public LocalDateTime localDateTime;
}
