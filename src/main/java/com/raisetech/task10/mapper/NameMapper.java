package com.raisetech.task10.mapper;

import com.raisetech.task10.CreateForm;
import com.raisetech.task10.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    @Select("SELECT * FROM names")
    List<Student> findAll();

    @Select("SELECT * FROM names WHERE id = #{id}")
    Optional<Student> findById(int id);

    @Insert("INSERT INTO names (name) VALUES (#{name})")
    void create(CreateForm name);
}
