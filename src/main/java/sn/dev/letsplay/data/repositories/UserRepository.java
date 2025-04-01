package sn.dev.letsplay.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.dev.letsplay.data.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
}
