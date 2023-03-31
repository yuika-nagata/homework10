package com.raisetech.task10.service;

import com.raisetech.task10.entity.Student;
import com.raisetech.task10.exception.ResourceNotFoundException;
import com.raisetech.task10.form.CreateForm;
import com.raisetech.task10.mapper.NameMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NameServiceImplTest {

    @InjectMocks
    NameServiceImpl nameServiceImpl;

    @Mock
    NameMapper nameMapper;

    @Test
    public void Studentテーブルにレコードがある時全てのユーザーがリストで返えされること() {
        List<Student> user = List.of(new Student("1", "nagata"), new Student("2", "tanaka"));
        doReturn(user).when(nameMapper).findAll();
        List<Student> actual = nameServiceImpl.findAll();
        assertThat(actual).isEqualTo(user);
    }

    @Test
    public void Studentテーブルに指定したidのレコードが存在する時にOptionalで返す() {
        Student user = new Student("1", "nagata");
        doReturn(Optional.of(user)).when(nameMapper).findById(1);
        Student actual = nameServiceImpl.findById(1);
        assertThat(actual).isEqualTo(user);
    }

    @Test
    public void 指定したidが存在しない時例外をthrowすること() {
        doReturn(Optional.empty()).when(nameMapper).findById(1);
        assertThatThrownBy(() -> nameServiceImpl.findById(1))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("resource not found");
    }

    @Test
    public void 名前が登録ができること() {

        CreateForm user = new CreateForm("name", "id");
        doNothing().when(nameMapper).createUser(user);
        nameServiceImpl.createUser(user);
        verify(nameMapper).createUser(user);
    }

    @Test
    public void 名前が更新できること() {

        CreateForm user = new CreateForm("name", "id");
        doReturn(Optional.of(new Student("1", "nagata"))).when(nameMapper).findById(1);
        nameServiceImpl.updateUser(1, user);
        verify(nameMapper).update(user);
    }

    @Test
    public void 更新対象のidが存在しないときに例外をthrowすること() {
        doReturn(Optional.empty()).when(nameMapper).findById(1);
        assertThatThrownBy(() -> nameServiceImpl.updateUser(1, new CreateForm("name", "id")))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("resource not found");
    }

    @Test
    public void 名前が削除できること() {
        doReturn(Optional.of(new Student("1", "nagata"))).when(nameMapper).findById(1);
        nameServiceImpl.deleteById(1);
        verify(nameMapper).deleteById(1);
    }

    @Test
    public void 削除対象のidが存在しないときに例外をthrowすること() {
        doReturn(Optional.empty()).when(nameMapper).findById(1);
        assertThatThrownBy(() -> nameServiceImpl.deleteById(1))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("resource not found");
    }
}
