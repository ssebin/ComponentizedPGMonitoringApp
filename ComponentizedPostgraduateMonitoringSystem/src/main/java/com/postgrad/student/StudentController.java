package com.postgrad.student;

import com.postgrad.base.ProgressUpdate;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public boolean editStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        return studentService.editStudentDetails(
                id,
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getResearchTopic()
        );
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        return studentService.removeStudent(id);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    // Progress Update Management

    @PostMapping("/{id}/progress-updates")
    public boolean addProgressUpdate(@PathVariable String id, @RequestBody ProgressUpdate update) {
        return studentService.addProgressUpdate(id, update);
    }

    @DeleteMapping("/{id}/progress-updates")
    public boolean removeProgressUpdate(@PathVariable String id, @RequestParam Date date) {
        return studentService.removeProgressUpdate(id, date);
    }

    @PutMapping("/{id}/progress-updates")
    public boolean editProgressUpdate(@PathVariable String id, @RequestParam Date date, @RequestParam String content) {
        return studentService.editProgressUpdate(id, date, content);
    }

    @GetMapping("/{id}/progress-updates")
    public List<ProgressUpdate> viewProgressUpdates(@PathVariable String id) {
        return studentService.viewProgressUpdatesOfStudent(id);
    }
}