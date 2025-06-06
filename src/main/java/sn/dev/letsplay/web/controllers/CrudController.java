package sn.dev.letsplay.web.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public interface CrudController<T, E> {
    CollectionModel<EntityModel<T>> getAll();
    EntityModel<T> getById(String id);
    ResponseEntity<T>  create(E obj);
    ResponseEntity<T> update(String id, E obj);
    ResponseEntity<Void> delete(String id);
}
