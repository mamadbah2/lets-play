package sn.dev.letsplay.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.repositories.UserRepository;
import sn.dev.letsplay.exceptions.ResourceNotFoundException;
import sn.dev.letsplay.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(String Id) {
        return userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + Id));
    }

    @Override
    public User create(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User update(User obj) {
        User user = getById(obj.getId());
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPassword(obj.getPassword());
        user.setRole(obj.getRole());
        return userRepository.save(user);
    }

    @Override
    public void delete(String Id) {
        if (userRepository.existsById(Id)) {
            userRepository.deleteById(Id);
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + Id);
        }
    }
}
