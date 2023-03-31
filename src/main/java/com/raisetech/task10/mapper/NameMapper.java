package com.raisetech.task10.mapper;

import com.raisetech.task10.form.CreateForm;
import com.raisetech.task10.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    //Studentテーブルにレコードがある時
    //全て取得すること
    //Studentsテーブルにレコードが無いとき
    //空のListを返すこと
    @Select("SELECT * FROM names")
    List<Student> findAll();

    //指定したidに紐ずくユーザーのレコードが存在する時
    //取得できること
    //指定したidが存在しない時
    //取得できない（空のOptionalを返す）
    @Select("SELECT * FROM names WHERE id = #{id}")
    Optional<Student> findById(int id);

    //指定した名前で新しくユーザーが登録できること
    @Insert("INSERT INTO names (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(CreateForm name);

    //指定したidに紐ずくユーザーが存在する時
    //更新できること
    //指定したidに紐ずくユーザーが存在しない時
    //更新されないこと
    @Update("UPDATE names SET name = #{name} WHERE id = #{id}")
    void update(CreateForm name);

    //指定したidに紐ずくユーザーが存在する時
    //削除する
    //指定したidに紐ずくユーザーが存在しない時
    //削除されない
    @Delete("DELETE FROM names WHERE id = #{id}")
    void deleteById(int id);
}
