package sn.dev.letsplay.services;

import sn.dev.letsplay.data.entities.Product;
import sn.dev.letsplay.data.entities.User;

import java.util.List;

public interface ProductService extends CrudService<Product> {
    List<Product> getProductsByUser(User user);
}
