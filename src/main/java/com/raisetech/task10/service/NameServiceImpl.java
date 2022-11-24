package com.raisetech.task10.service;

import com.raisetech.task10.exception.ResourceNotFoundException;
import com.raisetech.task10.entity.Name;
import com.raisetech.task10.mapper.NameMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NameServiceImpl implements NameService {

    private final NameMapper nameMapper;

    public NameServiceImpl(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    @Override
    public List<Name> findAll() {
        return nameMapper.findAll();
    }

    @Override
    public Name findById(int id) {
        Optional<Name> user = nameMapper.findById(id);
        return nameMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }
}
