package com.javadeveloperblogs.app.ws.ui.model.response;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {
    private String message;
    private Boolean success;
    private LocalDateTime timestamp;

    public static MessageResponseDto success(String message) {
        return MessageResponseDto.builder()
                .message(message)
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static MessageResponseDto error(String message) {
        return MessageResponseDto.builder()
                .message(message)
                .success(false)
                .timestamp(LocalDateTime.now())
                .build();
    }
}