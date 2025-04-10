package sn.dev.letsplay.services.implementation;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.repositories.UserRepository;
import sn.dev.letsplay.exceptions.ResourceNotFoundException;
import sn.dev.letsplay.services.JWTService;
import sn.dev.letsplay.services.UserService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
//    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostAuthorize("hasRole('Admin') or returnObject.username == #principal.username")// ou se get soi meme
    @Override
    public User getById(String Id) {
        return userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + Id));
    }

    @Override
    @PermitAll
    public User create(User obj) {
        if (userRepository.existsUserByUsername(obj.getUsername()) ||  userRepository.existsUserByEmail(obj.getEmail())) {
            System.out.println("User already exists");
            throw new RuntimeException("User already exist");
        }
        obj.setPassword(encoder.encode(obj.getPassword()));
        return userRepository.save(obj);
    }

    @Override
    @PostAuthorize("hasRole('Admin') or returnObject.username == authentication.name")
    public User update(User obj, String Id) {

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + Id));

        user.setUsername(obj.getUsername());
        user.setEmail(obj.getEmail());
        user.setPassword(encoder.encode(obj.getPassword()));
        user.setRole(obj.getRole());
        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('Admin')")
    public void delete(String Id) {
        if (userRepository.existsById(Id)) {
            userRepository.deleteById(Id);
        } else {
            System.out.println("User not found - Delete");
            throw new ResourceNotFoundException("User not found with ID: " + Id);
        }
    }



    @Override
    public String check(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        } catch (AuthenticationException e) {
            System.out.println( e.getMessage());
            System.out.println(user);
            System.out.println(Arrays.stream(e.getStackTrace()).toList());
            throw new AuthenticationCredentialsNotFoundException("Invalid username or password");
        }
        return "fail";
    }
}
