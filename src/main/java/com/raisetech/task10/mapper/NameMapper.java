package com.raisetech.task10.mapper;

import com.raisetech.task10.form.CreateForm;
import com.raisetech.task10.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    @Select("SELECT * FROM names")
    List<Student> findAll();

    @Select("SELECT * FROM names WHERE id = #{id}")
    Optional<Student> findById(int id);

    @Insert("INSERT INTO names (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(CreateForm name);

    @Update("UPDATE names SET name = #{name} WHERE id = #{id}")
    void update(CreateForm name);
}
