package com.assignment.eventManagementSystem.dto;

import com.assignment.eventManagementSystem.entity.Attendance;
import com.assignment.eventManagementSystem.entity.NewUser;
import com.assignment.eventManagementSystem.entity.Visibility;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private List<Attendance> attendances ;
    private UUID hostId;
    private UUID eventId;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Visibility visibility;
    private String location;
    private String description;
    private String title;

}
