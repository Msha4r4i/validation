package com.example.project1.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "id cannot be null")
    @Size(min = 3,max = 10, message = "id must be between 3 and 10 characters")
    private String id;

    @NotNull(message = "title cannot be null")
    @Size(min = 9,message = "title must be more than 8 characters")
    private String title;

    @NotNull(message = "description cannot be null")
    @Size(min = 16,message = "description must be more than 15 characters")
    private String description;

    @NotNull(message ="status cannot be null" )
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;

    @NotNull(message = "companyName cannot be null")
    @Size(min = 7,message = "company must be at least 6 characters")
    private String companyName;


}
