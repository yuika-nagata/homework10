package com.raisetech.task10.service;

import com.raisetech.task10.entity.Student;
import com.raisetech.task10.form.CreateForm;

import java.util.List;

public interface NameService {
    List<Student> findAll();

    Student findById(int id);

    void createUser(CreateForm name);

    void updateUser(int id, CreateForm name);

    void deleteById(int id);

}
