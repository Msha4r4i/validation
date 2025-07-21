package com.example.eventvalidation.Controller;


import com.example.eventvalidation.Api.Api;
import com.example.eventvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getEvents() {
        return ResponseEntity.status(200).body(events);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new Api ("Event added successfully"));
    }
    @PutMapping("/up/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable String id ,@Valid @RequestBody Event event, Errors errors) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                if (errors.hasErrors()){
                    String message = errors.getFieldError().getDefaultMessage();
                    return ResponseEntity.status(400).body(message);
                }
                e.setId(event.getId());
                e.setDescription(event.getDescription());
                e.setStartDate(event.getStartDate());
                e.setEndDate(event.getEndDate());
                e.setCapacity(event.getCapacity());
                return ResponseEntity.status(200).body(new Api ("Event updated successfully"));
            }
        } return ResponseEntity.status(400).body(new Api ("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                events.remove(e);
                return ResponseEntity.status(200).body(new Api ("Event deleted successfully"));
            }
        }return ResponseEntity.status(400).body(new Api ("id not found"));
    }
    @PutMapping("/change/{id}/{capacity}")
    public ResponseEntity<?> changeCapacity(@PathVariable String id, @PathVariable int capacity) {
    for (Event e : events) {
        if (e.getId().equals(id)) {
            e.setCapacity(capacity);
            return ResponseEntity.status(200).body(new Api ("Event changed successfully"));
        }
    }
    return ResponseEntity.status(400).body(new Api ("capacity not found"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchEvents(@PathVariable String id) {
        ArrayList<Event> events2 = new ArrayList<>();
        for (Event e : events) {
            if (e.getId().equals(id)) {
                events2.add(e);
            }
        }  if (events2.isEmpty()) {
            return ResponseEntity.status(400).body(new Api ("event not found"));
        }
        return ResponseEntity.status(200).body(events2);



    }







}
