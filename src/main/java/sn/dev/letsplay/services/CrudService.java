package sn.dev.letsplay.services;

import java.util.List;

public interface CrudService<POJO> {
    List<POJO> getAll();
    POJO getById(String Id);
    POJO create(POJO obj);
    POJO update(POJO obj);
    void delete(String Id);
}
