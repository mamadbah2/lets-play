package sn.dev.letsplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.repositories.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class LetsPlayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsPlayApplication.class, args);
    }

}
