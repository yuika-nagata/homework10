package com.raisetech.task10;

import java.util.List;
import java.util.Optional;

public interface NameService {
    List<Name> findAll();

    Optional<Name> findById(int id);

}
