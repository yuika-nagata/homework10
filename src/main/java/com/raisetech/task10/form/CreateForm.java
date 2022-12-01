package com.raisetech.task10.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CreateForm {

    @NotBlank
    @Length(max = 20)
    private String name;

    public CreateForm(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private static String id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
