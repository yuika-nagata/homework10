package com.raisetech.task10.controller;

import com.raisetech.task10.CreateForm;
import com.raisetech.task10.service.NameService;
import com.raisetech.task10.entity.Student;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/names")
    public List<Student> getNames() {
        return nameService.findAll();
    }

    @GetMapping("/names/{id}")
    public Student getUser(@PathVariable("id") int id) throws Exception {
        return nameService.findById(id);
    }

    @PostMapping
    public Map<String, String> createUser(@RequestBody @Validated CreateForm form) {
        nameService.createUser(form);
        return Map.of("message", "登録が完了しました。");
    }
}
