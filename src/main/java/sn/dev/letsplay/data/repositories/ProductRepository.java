package sn.dev.letsplay.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.dev.letsplay.data.entities.Product;


public interface ProductRepository extends MongoRepository<Product, String> {}
