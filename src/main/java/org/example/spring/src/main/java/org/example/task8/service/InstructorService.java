package org.example.task8.service;


import org.example.task8.dto.instructordto.InstructorDetailsDTO;
import org.example.task8.model.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor createInstructor(Instructor instructor);

    List<Instructor> getAllInstructors();

    Instructor getInstructorById(Long id);

    InstructorDetailsDTO getInstructorDetails(Long id);
}