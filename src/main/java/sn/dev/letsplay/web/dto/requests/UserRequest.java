package sn.dev.letsplay.web.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import sn.dev.letsplay.data.entities.User;

public class UserRequest {
    private String id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Min(value = 4, message = "password must have more than 3 character")
    private String password;
    @NotBlank(message = "role cannot be blank")
    private String role;

    public User toEntity() {
        User user = new User();
        user.setRole(this.role);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setName(this.name);
        user.setId(this.id);
        return user;
    }
}
