package com.postgrad.supervisor;

import com.postgrad.student.Student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supervisors")
public class SupervisorController {
    private final SupervisorServiceImpl supervisorService;

    public SupervisorController(SupervisorServiceImpl supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @PostMapping
    public void addSupervisor(@RequestBody Supervisor supervisor) {
        supervisorService.addSupervisor(supervisor);
    }

    @PutMapping("/{id}")
    public boolean editSupervisor(@PathVariable String id, @RequestBody Supervisor updatedSupervisor) {
        return supervisorService.editSupervisorDetails(
                id,
                updatedSupervisor.getName(),
                updatedSupervisor.getEmail(),
                updatedSupervisor.getOfficeRoom()
        );
    }

    @DeleteMapping("/{id}")
    public boolean deleteSupervisor(@PathVariable String id) {
        return supervisorService.removeSupervisor(id);
    }

    @GetMapping("/{id}")
    public Supervisor getSupervisorById(@PathVariable String id) {
        return supervisorService.getSupervisorById(id);
    }

    @PostMapping("/{id}/students/{studentId}")
    public boolean assignSupervisor(@PathVariable String id, @PathVariable String studentId) {
        return supervisorService.assignSupervisorToStudent(studentId, id);
    }

    @DeleteMapping("/students/{studentId}")
    public boolean removeSupervisorFromStudent(@PathVariable String studentId) {
        return supervisorService.removeSupervisorFromStudent(studentId);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsUnderSupervisor(@PathVariable String id) {
        return supervisorService.getStudentsUnderSupervisor(id);
    }

    @GetMapping("/students-without-supervisor")
    public List<Student> viewStudentsWithoutSupervisor() {
        return supervisorService.viewStudentsWithoutSupervisor();
    }
}