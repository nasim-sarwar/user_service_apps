package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkUserResponseDto {
    private Integer successCount;
    private Integer failureCount;
    private java.util.List<UserResponseDto> createdUsers;
    private java.util.List<String> errors;
}
