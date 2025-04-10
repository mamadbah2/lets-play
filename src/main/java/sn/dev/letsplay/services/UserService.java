package sn.dev.letsplay.services;


import sn.dev.letsplay.data.entities.User;

public interface UserService extends CrudService<User> {
    String check(User user);
}
