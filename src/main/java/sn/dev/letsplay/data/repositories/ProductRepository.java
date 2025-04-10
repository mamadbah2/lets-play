package sn.dev.letsplay.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.dev.letsplay.data.entities.Product;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getProductsByUserId(String userId);
}
