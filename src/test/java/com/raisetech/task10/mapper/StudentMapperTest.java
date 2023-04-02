package com.raisetech.task10.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.task10.entity.Student;
import com.raisetech.task10.form.StudentForm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;


    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void ユーザーが全権取得できること() {
        List<Student> students = studentMapper.findAll();
        List<Student> user = List.of(students.get(0), students.get(1), students.get(2));
        assertThat(students)
                .hasSize(3)
                .isEqualTo(user);
    }

    @Test
    @DataSet(value = "empty.yml")
    void 対象のユーザーが存在しない場合に空のListが取得できること() {
        Optional<Student> students = studentMapper.findById(1);
        assertThat(students).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    void 対象のユーザーが存在する時対象のユーザーを1件取得できること() {
        Optional<Student> student = studentMapper.findById(1);
        assertThat(student).contains(student.get());
    }

    @Test
    @DataSet(value = "datasets/empty.yml")
    @ExpectedDataSet(value = "expectedAfterInsertStudents.yml", ignoreCols = "id")
    void ユーザーが登録できること() {
        studentMapper.createUser(new StudentForm("nagata", "1"));
        assertThat(studentMapper.findById(1)).isNotNull();
        studentMapper.createUser(new StudentForm("tanaka", "2"));
        assertThat(studentMapper.findById(2)).isNotNull();
        studentMapper.createUser(new StudentForm("yamamoto", "3"));
        assertThat(studentMapper.findById(3)).isNotNull();
    }

    @Test
    @DataSet(value = "datasets/update.yml")
    @ExpectedDataSet(value = "expectedAfterUpdateStudents.yml")
    void ユーザーが更新できること() {
        studentMapper.update(new StudentForm("nagata", "1"));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "expectedAfterDeleteStudents.yml")
    void 対象のユーザーが1件削除できること() {
        assertThat(studentMapper.findAll())
                .hasSize(3);
        studentMapper.deleteById(1);
        assertThat(studentMapper.findAll())
                .hasSize(2);
    }

}
