package com.assignment.eventManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendances", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "event_id"})
})
@Data
public class Attendance {

    @EmbeddedId
    private AttendanceId id = new AttendanceId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private NewUser user;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private LocalDateTime respondedAt;
}
