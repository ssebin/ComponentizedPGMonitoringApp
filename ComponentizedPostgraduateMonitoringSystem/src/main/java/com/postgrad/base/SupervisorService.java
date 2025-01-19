package com.postgrad.base;

import com.postgrad.student.Student;
import com.postgrad.supervisor.Supervisor;

import java.util.List;

public interface SupervisorService {
    void addSupervisor(Supervisor supervisor);
    boolean removeSupervisor(String supervisorId);
    boolean editSupervisorDetails(String supervisorId, String name, String email, String officeRoom);
    Supervisor getSupervisorById(String supervisorId);
    List<Supervisor> getAllSupervisors();

    // Supervisor Appointment
    boolean assignSupervisorToStudent(String studentId, String supervisorId);
    boolean removeSupervisorFromStudent(String studentId);
    List<Student> viewStudentsWithoutSupervisor();
    List<Student> getStudentsUnderSupervisor(String supervisorId);
}