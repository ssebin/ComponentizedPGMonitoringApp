package com.postgrad.base;

import com.postgrad.student.Student;

import java.util.Date;
import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    boolean removeStudent(String studentId);
    boolean editStudentDetails(String studentId, String name, String email, String researchTopic);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();

    // Progress Update Management
    boolean addProgressUpdate(String studentId, ProgressUpdate progressUpdate);
    boolean removeProgressUpdate(String studentId, Date updateDate);
    boolean editProgressUpdate(String studentId, Date updateDate, String newContent);
    List<ProgressUpdate> viewProgressUpdatesOfStudent(String studentId);
}