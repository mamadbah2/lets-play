package sn.dev.letsplay.services;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import sn.dev.letsplay.data.entities.User;

public interface UserService extends CrudService<User> {
    String check(User user);
}
