package sn.dev.letsplay.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Document("Users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String email;
    private String password;
    private String role;
}
