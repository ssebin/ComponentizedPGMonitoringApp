package com.postgrad.supervisor;

import com.postgrad.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Supervisor {
    private String supervisorId;
    private String name;
    private String email;
    private String officeRoom;
    private List<Student> students; // Association with Students

    // Constructor
    public Supervisor(String supervisorId, String name, String email, String officeRoom) {
        this.supervisorId = supervisorId;
        this.name = name;
        this.email = email;
        this.officeRoom = officeRoom;
        this.students = new ArrayList<>();
    }

    // Getters and Setters
    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom;
    }

    public List<Student> getStudents() {
        return students;
    }

    // Methods to manage students
    public void addStudent(Student student) {
        students.add(student);
        student.setSupervisor(this);
    }

    public boolean removeStudent(Student student) {
        if (students.remove(student)) {
            student.removeSupervisor();
            return true;
        }
        return false;
    }

    // toString method
    @Override
    public String toString() {
        return "Supervisor Details:\n" +
               "-----------------------------\n" +
               "ID        : " + supervisorId + "\n" +
               "Name      : " + name + "\n" +
               "Email     : " + email + "\n" +
               "Office    : " + officeRoom + "\n" +
               "-----------------------------";
    }
}