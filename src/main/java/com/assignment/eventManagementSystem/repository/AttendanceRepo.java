package com.assignment.eventManagementSystem.repository;

import com.assignment.eventManagementSystem.entity.Attendance;
import com.assignment.eventManagementSystem.entity.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<Attendance, AttendanceId> {
}
