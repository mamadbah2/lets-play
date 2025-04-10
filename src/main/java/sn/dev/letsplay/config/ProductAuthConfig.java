package sn.dev.letsplay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sn.dev.letsplay.data.entities.Product;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.repositories.ProductRepository;
import sn.dev.letsplay.data.repositories.UserRepository;

import java.util.Optional;

@Component("productAuthConfig")
@RequiredArgsConstructor
public class ProductAuthConfig {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public boolean isOwner(String productId, Authentication authentication) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();

        User user = userRepository.findUserByUsername(authentication.getName()) ;
        return product.getUserId().equalsIgnoreCase(user.getId());
    }
}