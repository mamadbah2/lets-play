package sn.dev.letsplay.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.dev.letsplay.data.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
