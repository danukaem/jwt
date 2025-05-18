package com.assignment.eventManagementSystem.controller;

import com.assignment.eventManagementSystem.dto.AttendanceDTO;
import com.assignment.eventManagementSystem.dto.EventDTO;
import com.assignment.eventManagementSystem.dto.NewUserDTO;
import com.assignment.eventManagementSystem.dto.ResponseDTO;
import com.assignment.eventManagementSystem.entity.Attendance;
import com.assignment.eventManagementSystem.entity.Event;
import com.assignment.eventManagementSystem.entity.NewUser;
import com.assignment.eventManagementSystem.repository.AttendanceRepo;
import com.assignment.eventManagementSystem.repository.EventRepo;
import com.assignment.eventManagementSystem.repository.NewUserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("newUser")
public class NewUserController {

    @Autowired
    private NewUserRepo userRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @PostMapping("saveNewUser")
    public ResponseEntity<?> saveNewUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        NewUser newUser = new NewUser();
        newUser.setName(newUserDTO.getName());
        newUser.setEmail(newUserDTO.getEmail());
        newUser.setPassword(newUserDTO.getPassword());
        newUser.setRole(newUserDTO.getRole());
        NewUser save = userRepo.save(newUser);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setMessage("User created successfully");
        responseDTO.setData(save);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("saveEvent")
    public ResponseEntity<?> saveEvent(@Valid @RequestBody EventDTO eventDTO) {
        Event event = new Event();
        event.setAttendances(eventDTO.getAttendances());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setVisibility(eventDTO.getVisibility());
        event.setStartTime(eventDTO.getStartTime());
        event.setEndTime(eventDTO.getEndTime());
        event.setHost(userRepo.findById(eventDTO.getHostId()).get());
        Event savedEvent = eventRepo.save(event);
        eventDTO.setAttendances(savedEvent.getAttendances());
        eventDTO.setEventId(savedEvent.getId());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setMessage("Event created successfully");
        responseDTO.setData(eventDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("saveAttendance")
    public ResponseEntity<?> saveAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setUser(userRepo.findById(attendanceDTO.getUserId()).get());
        attendance.setEvent(eventRepo.findById(attendanceDTO.getEventId()).get());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRespondedAt(attendanceDTO.getRespondedAt());

        attendanceRepo.save(attendance);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setMessage("Attendance created successfully");
        responseDTO.setData(attendanceDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
