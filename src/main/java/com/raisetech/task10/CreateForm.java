package com.raisetech.task10;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CreateForm {

    @NotBlank
    @Length(max = 20)
    private String name;

    public String getName() {
        return name;
    }
}
