package org.example.task8.instructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // Create an instructor
    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorService.createInstructor(instructor);
    }

    // Get all instructors
    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }
    @GetMapping("/{id}")
    public InstructorDetailsDTO getInstructorDetails(@PathVariable Long id) {
        return instructorService.getInstructorDetails(id);
    }
}