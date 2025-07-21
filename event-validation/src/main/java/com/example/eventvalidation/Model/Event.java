package com.example.eventvalidation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

    @NotNull(message = "id cannot be null")
    @Size(min = 3, message = "id must be at least 3 characters")
    private String id;

    @NotNull(message = "description cannot be null")
    @Size(min = 16,message ="description must be at least 16 characters")
    private String description;

    @NotNull(message = "capacity cannot be null")
    @Min(value = 26, message = "capacity must be at least 26")
    private Integer capacity;

    @NotNull(message ="startDate cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @NotNull(message = "endDate cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


}
