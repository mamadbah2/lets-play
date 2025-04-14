package sn.dev.letsplay.web.controllers.implementation;

import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dev.letsplay.services.CrudService;
import sn.dev.letsplay.web.controllers.CrudController;

import java.util.List;

public abstract class AbstractCrudController<T, E, POJO> implements CrudController<T, E> {
    protected abstract CrudService<POJO> getService();

    protected abstract EntityModel<T> mapDtoToModel(T obj);

    protected abstract T mapPovoToDto(POJO obj);

    protected abstract POJO mapDtoToPovo(E obj);

    @Override
    @GetMapping("/")
    public CollectionModel<EntityModel<T>> getAll() {
        List<POJO> all = getService().getAll();
        List<T> resp = all.stream().map(this::mapPovoToDto).toList();
        List<EntityModel<T>> respModel = resp.stream().map(this::mapDtoToModel).toList();
        return CollectionModel.of(
                respModel,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withSelfRel()
        );
    }

    @Override
    @GetMapping("/{id}")
    public EntityModel<T> getById(@PathVariable String id) {
        T data = mapPovoToDto(getService().getById(id));
        return mapDtoToModel(data);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<T> create(@Valid @RequestBody E obj) {
        POJO data = mapDtoToPovo(obj);
        POJO createdData = getService().create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapPovoToDto(createdData));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable String id, @Valid @RequestBody E obj) {
        POJO data = mapDtoToPovo(obj);
        POJO updatedData = getService().update(data, id);
        return ResponseEntity.ok(mapPovoToDto(updatedData));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
