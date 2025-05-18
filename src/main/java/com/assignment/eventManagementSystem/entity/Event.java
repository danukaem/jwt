package com.assignment.eventManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Data
public class Event extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private NewUser host;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances = new ArrayList<>();
}
