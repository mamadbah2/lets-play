package sn.dev.letsplay.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public interface CrudController<T, E> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T>  getById(String id);
    ResponseEntity<T>  create(E obj);
    ResponseEntity<T> update(E obj);
    ResponseEntity<Void> delete(String id);
}
