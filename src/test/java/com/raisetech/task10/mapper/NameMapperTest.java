package com.raisetech.task10.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.task10.entity.Student;
import com.raisetech.task10.form.CreateForm;

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
class NameMapperTest {
    @Autowired
    NameMapper nameMapper;


    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void すべてのユーザーが取得できること() {
        List<Student> students = nameMapper.findAll();
        List<Student> user = List.of(students.get(0), students.get(1), students.get(2));
        assertThat(students)
                .hasSize(3)
                .isEqualTo(user);
    }

    @Test
    @DataSet(value = "empty.yml")
    void 指定したidが存在しない場合に空のListが取得できること() {
        Optional<Student> students = nameMapper.findById(1);
        assertThat(students).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    void 引数のidに対応した名前を取得できること() {
        Optional<Student> student = nameMapper.findById(1);
        assertThat(student).contains(student.get());
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    void 引数のidに対応したユーザーが存在しない時_空のOptionalを取得すること() {
        Optional<Student> student = nameMapper.findById(4);
        assertThat(student).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/empty.yml")
    @ExpectedDataSet(value = "expectedAfterInsertStudents.yml", ignoreCols = "id")
    void 名前が登録できること() {
        nameMapper.createUser(new CreateForm("nagata", "1"));
        assertThat(nameMapper.findById(1)).isNotNull();
        nameMapper.createUser(new CreateForm("tanaka", "2"));
        assertThat(nameMapper.findById(2)).isNotNull();
        nameMapper.createUser(new CreateForm("yamamoto", "3"));
        assertThat(nameMapper.findById(3)).isNotNull();
    }

    @Test
    @DataSet(value = "datasets/Update.yml")
    @ExpectedDataSet(value = "expectedAfterUpdateStudents.yml")
    void 名前が更新できること() {
        nameMapper.update(new CreateForm("nagata", "1"));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "expectedAfterDeleteStudents.yml")
    void 名前が削除できること() {
        nameMapper.deleteById(1);
    }

}
