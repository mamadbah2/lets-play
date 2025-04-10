package sn.dev.letsplay.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sn.dev.letsplay.data.entities.Product;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.data.repositories.ProductRepository;
import sn.dev.letsplay.exceptions.ResourceNotFoundException;
import sn.dev.letsplay.services.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(String Id) {
        return productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + Id));
    }

    @Override
    public Product create(Product obj) {
        return productRepository.save(obj);
    }

    @Override
    @PostAuthorize("hasRole('Admin') or @productAuthConfig.isOwner(#Id, authentication)") // ou soi meme etre le possesseur
    public Product update(Product obj, String Id) {
        Product product = getById(Id);
        product.setPrice(obj.getPrice());
        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        return productRepository.save(product);
    }

    @Override
    @PostAuthorize("hasRole('Admin') or @productAuthConfig.isOwner(#Id, authentication)")
    public void delete(String Id) {
        if (productRepository.existsById(Id)) {
            productRepository.deleteById(Id);
        } else {
            throw new ResourceNotFoundException("Product not found with ID: " + Id);
        }
    }

    @Override
//    @PostAuthorize("hasRole('Admin') or #user.username == principal.username")
    public List<Product> getProductsByUser(User user) {
        return productRepository.getProductsByUserId(user.getId());
    }
}
