package com.raisetech.task10.service;

import com.raisetech.task10.entity.Student;
import com.raisetech.task10.exception.ResourceNotFoundException;
import com.raisetech.task10.form.StudentForm;
import com.raisetech.task10.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    //Studentテーブルにレコードがある時
    //全てnameMapperのfindAll()にリストで返す
    //Studentテーブルにレコードが無い時
    //空のリストをnameMapperのfindAll()に返す
    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    //Studentテーブルに指定したidのレコードが存在する時
    //nameMapperのfindById(id)にOptionalで返す
    //studentテーブルに指定したレコードが存在しない時
    //例外をthrowする
    @Override
    public Student findById(int id) {
        Optional<Student> user = studentMapper.findById(id);
        return studentMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }

    //指定した名前でnameMapperのcreateUser(name)に
    @Override
    public void createUser(StudentForm name) {
        studentMapper.createUser(name);
    }

    //指定したidが存在する時
    //nameMapperのupdate(name)に
    //指定したidが存在しない時
    //例外をthrowする
    @Override
    public void updateUser(int id, StudentForm name) {
        studentMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        studentMapper.update(name);
    }

    //指定したidが存在する時
    //nameMapperのdeleteById(id)に引数を渡す
    //指定したidが存在しない時
    //例外をthrowする
    @Override
    public void deleteById(int id) {
        studentMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        studentMapper.deleteById(id);
    }
}
