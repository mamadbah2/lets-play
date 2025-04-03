package sn.dev.letsplay.web.controllers.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.services.CrudService;
import sn.dev.letsplay.services.UserService;
import sn.dev.letsplay.web.dto.requests.UserRequest;
import sn.dev.letsplay.web.dto.responses.UserResponse;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserControllerImpl extends AbstractCrudController<UserResponse, UserRequest, User> {

    private final UserService userService;

    @Override
    protected CrudService<User> getService() {
        return userService;
    }

    @Override
    protected UserResponse mapPovoToDto(User obj) {
        return null;
    }

    @Override
    protected User mapDtoToPovo(UserRequest obj) {
        return null;
    }
}
