package sn.dev.letsplay.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("User")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
