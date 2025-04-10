package sn.dev.letsplay.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.entities.UserPrincipal;
import sn.dev.letsplay.data.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
//        User user = userRepository.findUserByUsername(username);
        User user = userRepository.findUserByUsername(username);

        System.out.println(user);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("username not found");
        }
        return new UserPrincipal(user);
    }
}
