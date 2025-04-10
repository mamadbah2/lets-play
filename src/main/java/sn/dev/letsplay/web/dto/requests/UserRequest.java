package sn.dev.letsplay.web.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sn.dev.letsplay.data.entities.User;

@Data
public class UserRequest {
    private String id;
    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "email cannot be blank")
    @Email(message = "your email is not in a valid format")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 4, message = "password must have more than 3 character")
    private String password;
    private String role;

    public User toEntity() {
        User user = new User();
        user.setRole(this.role);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setId(this.id);
        return user;
    }
}
