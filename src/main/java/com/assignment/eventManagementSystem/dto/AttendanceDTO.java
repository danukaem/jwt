package com.assignment.eventManagementSystem.dto;

import com.assignment.eventManagementSystem.entity.AttendanceStatus;
import com.assignment.eventManagementSystem.entity.Event;
import com.assignment.eventManagementSystem.entity.NewUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AttendanceDTO {
    private UUID userId;
    private UUID eventId;
    private AttendanceStatus status;
    private LocalDateTime respondedAt;

}
