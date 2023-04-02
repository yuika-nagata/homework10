package com.raisetech.task10.service;

import com.raisetech.task10.entity.Student;
import com.raisetech.task10.form.StudentForm;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(int id);

    void createUser(StudentForm name);

    void updateUser(int id, StudentForm name);

    void deleteById(int id);

}
