package org.example.task10.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PostMapping("/{teacherId}/link/{studentId}")
    public Teacher linkStudent(@PathVariable Long teacherId, @PathVariable Long studentId) {
        return teacherService.linkStudent(teacherId, studentId);
    }

    // API 1: get all teacher and students related to this teacher
    @GetMapping
    public List<TeacherDetailsDTO> getAllTeachersWithStudents() {
        return teacherService.getAllTeachersWithStudents();
    }

    // API 2: get teacher and students related to this teacher by id
    @GetMapping("/{id}")
    public TeacherDetailsDTO getTeacherWithStudentsById(@PathVariable Long id) {
        return teacherService.getTeacherWithStudentsById(id);
    }
}
