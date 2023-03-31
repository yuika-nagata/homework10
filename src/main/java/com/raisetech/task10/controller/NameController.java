package com.raisetech.task10.controller;

import com.raisetech.task10.form.CreateForm;
import com.raisetech.task10.service.NameService;
import com.raisetech.task10.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    //Studentテーブルにレコードが存在する時
    //全て取得できること
    //studentテーブルにレコードが存在しない時
    //空のリストが返ってくること
    @GetMapping("/names")
    public List<Student> getNames() {
        return nameService.findAll();
    }

    //指定したidのレコードが存在存在する時
    //取得できること
    //指定したidのレコードが存在しない時
    //例外をthrowすること
    @GetMapping("/names/{id}")
    public Student getUser(@PathVariable("id") int id) throws Exception {
        return nameService.findById(id);
    }

    //指定した名前でユーザーが登録でき、レスポンスボディに"登録が完了しました"とメッセージが返ってくること
    @PostMapping("/names")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody @Validated CreateForm name, UriComponentsBuilder uriBuilder) {
        nameService.createUser(name);
        URI url = uriBuilder
                .path("names/" + name.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "登録が完了しました。"));
    }

    //指定したidに紐ずくユーザーが存在する時
    //更新できること
    //指定したidに紐ずくユーザーが存在しない時
    //更新できないこと
    @PatchMapping("names/{id}")
    public Map<String, String> updateUser(@PathVariable("id") int id, @RequestBody @Validated CreateForm name) {
        nameService.updateUser(id, name);
        return Map.of("message", "更新が完了しました。");
    }

    //指定したidに紐ずくユーザーが存在する時
    //削除できること
    //指定したidに紐ずくユーザーが存在しない時
    //削除されないこと
    @DeleteMapping("names/{id}")
    public Map<String, String> deleteUser(@PathVariable int id) throws Exception {
        nameService.deleteById(id);
        return Map.of("message", "削除しました。");
    }
}
