package com.raisetech.task10;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public Optional<Name> getUser(@PathVariable("id") int id) {
        return nameService.findById(id);
    }
}
