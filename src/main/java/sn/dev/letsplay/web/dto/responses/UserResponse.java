package sn.dev.letsplay.web.dto.responses;

import lombok.Data;
import sn.dev.letsplay.data.entities.User;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
