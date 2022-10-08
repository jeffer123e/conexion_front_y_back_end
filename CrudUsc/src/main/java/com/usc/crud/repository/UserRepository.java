package com.usc.crud.repository;

import com.usc.crud.model.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Empleado, Long> {
}
