package com.assignment.eventManagementSystem.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class AttendanceId implements Serializable {

    private UUID userId;

    private UUID eventId;

    // equals() and hashCode() required!
}
