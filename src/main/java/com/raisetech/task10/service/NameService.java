package com.raisetech.task10.service;

import com.raisetech.task10.CreateForm;
import com.raisetech.task10.entity.Student;

import java.util.List;

public interface NameService {
    List<Student> findAll();

    Student findById(int id);

    void createUser(CreateForm form);
}
