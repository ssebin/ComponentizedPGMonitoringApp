package com.postgrad.supervisor;

import com.postgrad.base.SupervisorService;
import com.postgrad.student.Student;
import com.postgrad.student.StudentServiceImpl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository supervisorRepository;
    private final StudentServiceImpl studentService;

    public SupervisorServiceImpl(SupervisorRepository supervisorRepository, StudentServiceImpl studentService) {
        this.supervisorRepository = supervisorRepository;
        this.studentService = studentService;
    }

    @Override
    public void addSupervisor(Supervisor supervisor) {
        supervisorRepository.save(supervisor);
    }

    @Override
    public boolean removeSupervisor(String supervisorId) {
        if (supervisorRepository.findById(supervisorId) != null) {
            supervisorRepository.deleteById(supervisorId);
            return true;
        }
        return false;
    }

    @Override
    public boolean editSupervisorDetails(String supervisorId, String name, String email, String officeRoom) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId);
        if (supervisor != null) {
            supervisor.setName(name);
            supervisor.setEmail(email);
            supervisor.setOfficeRoom(officeRoom);
            supervisorRepository.save(supervisor); // Update the repository
            return true;
        }
        return false;
    }

    @Override
    public Supervisor getSupervisorById(String supervisorId) {
        return supervisorRepository.findById(supervisorId);
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    // Supervisor Appointment Management

    @Override
    public boolean assignSupervisorToStudent(String studentId, String supervisorId) {
        Student student = studentService.getStudentById(studentId);
        Supervisor supervisor = supervisorRepository.findById(supervisorId);

        if (student != null && supervisor != null) {
            supervisor.addStudent(student);
            student.setSupervisor(supervisor);
            supervisorRepository.save(supervisor);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeSupervisorFromStudent(String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null && student.getSupervisor() != null) {
            Supervisor supervisor = student.getSupervisor();
            supervisor.removeStudent(student);
            student.removeSupervisor();
            supervisorRepository.save(supervisor);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> viewStudentsWithoutSupervisor() {
        List<Student> studentsWithoutSupervisor = new ArrayList<>();
        for (Student student : studentService.getAllStudents()) {
            if (student.getSupervisor() == null) {
                studentsWithoutSupervisor.add(student);
            }
        }
        return studentsWithoutSupervisor;
    }

    @Override
    public List<Student> getStudentsUnderSupervisor(String supervisorId) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId);
        return supervisor != null ? supervisor.getStudents() : new ArrayList<>();
    }
}