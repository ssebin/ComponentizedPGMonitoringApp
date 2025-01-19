package com.postgrad.student;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    public void save(Student student) {
        students.put(student.getStudentId(), student);
    }

    public Student findById(String studentId) {
        return students.get(studentId);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public void deleteById(String studentId) {
        students.remove(studentId);
    }
}