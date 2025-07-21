package com.example.project1.Controller;

import com.example.project1.Api.ApiRespons;
import com.example.project1.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiRespons("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateProject(@PathVariable int index, @Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiRespons("Project updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiRespons("Project deleted successfully"));
    }

    @PutMapping("/change/{id}/{status}")
    public ResponseEntity<?> changeProjectStatus(@PathVariable String id, @PathVariable String status) {
        for (Project p : projects) {
            if (p.getId().equals(id)) {
                if (!status.equals("In Progress") && !status.equals("Completed") && !status.equals("Not Started")) {
                    return ResponseEntity.status(400).body("Status must be 'In Progress or Completed or Not Started' '");
                }
                p.setStatus(status);
                return ResponseEntity.status(200).body(new ApiRespons("Project status changed successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiRespons("Project status not changed successfully"));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchProjectTitle(@PathVariable String title) {
        ArrayList<Project> projects1 = new ArrayList<>();

        for (Project p : projects) {
            if (p.getTitle().equals(title)) {
                projects1.add(p);
            }
        }
        if (!projects1.isEmpty()) {
            return ResponseEntity.status(200).body(projects1);
        }
        return ResponseEntity.status(400).body(new ApiRespons("Project title not found"));
    }

    @GetMapping("/company/{companyName}")
    public ResponseEntity<?> searchProjectName(@PathVariable String companyName) {
        ArrayList<Project> projects1 = new ArrayList<>();
        for (Project p : projects) {
            if (p.getCompanyName().equals(companyName)) {
                projects1.add(p);
            }
        }if (!projects1.isEmpty()) {
            return ResponseEntity.status(200).body(projects1);
        }return ResponseEntity.status(400).body(new ApiRespons("Project name not found"));
    }
}