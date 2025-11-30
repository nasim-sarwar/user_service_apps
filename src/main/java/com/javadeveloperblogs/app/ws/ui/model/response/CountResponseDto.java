package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountResponseDto {
    private Long count;
}