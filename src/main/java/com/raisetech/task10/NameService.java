package com.raisetech.task10;

import java.util.List;

public interface NameService {
    List<Name> findAll();

    Name findById(int id);
}
