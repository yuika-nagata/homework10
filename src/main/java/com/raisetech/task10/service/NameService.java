package com.raisetech.task10.service;

import com.raisetech.task10.entity.Name;

import java.util.List;

public interface NameService {
    List<Name> findAll();

    Name findById(int id);
}
