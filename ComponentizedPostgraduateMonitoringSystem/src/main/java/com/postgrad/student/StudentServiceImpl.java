package com.postgrad.student;

import com.postgrad.base.StudentService;
import com.postgrad.base.ProgressUpdate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public boolean removeStudent(String studentId) {
        return students.removeIf(student -> student.getStudentId().equals(studentId));
    }

    @Override
    public boolean editStudentDetails(String studentId, String name, String email, String researchTopic) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setResearchTopic(researchTopic);
            return true;
        }
        return false;
    }

    @Override
    public Student getStudentById(String studentId) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Progress Update Management

    @Override
    public boolean addProgressUpdate(String studentId, ProgressUpdate progressUpdate) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.addProgressUpdate(progressUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeProgressUpdate(String studentId, Date updateDate) {
        Student student = getStudentById(studentId);
        if (student != null) {
            return student.removeProgressUpdate(updateDate);
        }
        return false;
    }

    @Override
    public boolean editProgressUpdate(String studentId, Date updateDate, String newContent) {
        Student student = getStudentById(studentId);
        if (student != null) {
            return student.editProgressUpdate(updateDate, newContent);
        }
        return false;
    }

    @Override
    public List<ProgressUpdate> viewProgressUpdatesOfStudent(String studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            return student.getProgressUpdates();
        }
        return null;
    }
}