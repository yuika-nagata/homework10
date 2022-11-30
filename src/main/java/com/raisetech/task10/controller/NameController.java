package com.raisetech.task10.controller;

import com.raisetech.task10.CreateForm;
import com.raisetech.task10.service.NameService;
import com.raisetech.task10.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Map<String, String>> createUser(@RequestBody @Validated CreateForm name, UriComponentsBuilder uriBuilder) {
        nameService.createUser(name);
        URI url = uriBuilder
                .path("names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "登録が完了しました。"));
    }
}
