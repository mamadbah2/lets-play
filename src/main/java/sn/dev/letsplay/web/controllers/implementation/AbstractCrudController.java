package sn.dev.letsplay.web.controllers.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sn.dev.letsplay.services.CrudService;
import sn.dev.letsplay.web.controllers.CrudController;

import java.util.List;

public abstract class AbstractCrudController<T, E, POJO> implements CrudController<T, E> {
    protected abstract CrudService<POJO> getService();

    protected abstract T mapPovoToDto(POJO obj);

    protected abstract POJO mapDtoToPovo(E obj);

    @Override
    public ResponseEntity<List<T>> getAll() {
        List<POJO> all = getService().getAll();
        List<T> resp = all.stream().map(this::mapPovoToDto).toList();
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<T> getById(String id) {
        T data = mapPovoToDto(getService().getById(id));
        return ResponseEntity.ok(data);
    }

    @Override
    public ResponseEntity<T> create(E obj) {
        POJO data = mapDtoToPovo(obj);
        POJO createdData = getService().create(data);
        return ResponseEntity.ok(mapPovoToDto(createdData));
    }

    @Override
    public ResponseEntity<T> update(E obj) {
        POJO data = mapDtoToPovo(obj);
        POJO updatedData = getService().update(data);
        return ResponseEntity.ok(mapPovoToDto(updatedData));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
