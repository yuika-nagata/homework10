package com.raisetech.task10.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class StudentForm {

    //名前が20文字以内の場合
    //エラーにならない
    //名前が20文字以上だった場合
    //エラーになる
    @NotBlank
    @Length(max = 20)
    private String name;

    public StudentForm(String name, String id) {
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
