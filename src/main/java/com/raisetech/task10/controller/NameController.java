package com.raisetech.task10.controller;

import com.raisetech.task10.service.NameService;
import com.raisetech.task10.entity.Name;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/names")
    public List<Name> getNames() {
        return nameService.findAll();
    }

    @GetMapping("/names/{id}")
    public Name getUser(@PathVariable("id") int id) throws Exception {
        return nameService.findById(id);
    }
}
