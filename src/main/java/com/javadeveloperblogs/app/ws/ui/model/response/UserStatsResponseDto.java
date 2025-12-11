package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing aggregated statistics about users.
 * <p>
 * This DTO is used to return metrics such as total users, verified/unverified users,
 * active/inactive users, creation counts over different time periods, and verification rate.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsResponseDto {
    private Long totalUsers;
    private Long verifiedUsers;
    private Long unverifiedUsers;
    private Long usersCreatedToday;
    private Long usersCreatedThisWeek;
    private Long usersCreatedThisMonth;
}