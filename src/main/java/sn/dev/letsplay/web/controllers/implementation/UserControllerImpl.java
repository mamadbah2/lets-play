package sn.dev.letsplay.web.controllers.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dev.letsplay.data.entities.Product;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.services.CrudService;
import sn.dev.letsplay.services.ProductService;
import sn.dev.letsplay.services.UserService;
import sn.dev.letsplay.services.implementation.RateLimiterService;
import sn.dev.letsplay.web.assembler.ProductModelAssembler;
import sn.dev.letsplay.web.assembler.UserResponseModelAssembler;
import sn.dev.letsplay.web.controllers.UserController;
import sn.dev.letsplay.web.dto.requests.UserRequest;
import sn.dev.letsplay.web.dto.responses.ProductResponse;
import sn.dev.letsplay.web.dto.responses.UserResponse;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserControllerImpl extends AbstractCrudController<UserResponse, UserRequest, User> implements UserController {

    private final UserService userService;
    private final ProductService productService;
    private final UserResponseModelAssembler userResponseModelAssembler;
    private final ProductModelAssembler productModelAssembler;
    private final RateLimiterService rateLimiterService;

    @Override
    protected CrudService<User> getService() {
        return userService;
    }

    @Override
    protected EntityModel<UserResponse> mapDtoToModel(UserResponse obj) {
        return userResponseModelAssembler.toModel(obj);
    }

    @Override
    protected UserResponse mapPovoToDto(User obj) {
        return new UserResponse(obj);
    }

    @Override
    protected User mapDtoToPovo(UserRequest obj) {
        return obj.toEntity();
    }



    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody UserRequest userRequest) {
        if (!rateLimiterService.isAllowed(userRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too Many Request");
        }
        User user = mapDtoToPovo(userRequest);
        return ResponseEntity.ok(userService.check(user));
    }

    @Override
    @GetMapping("/{id}/products")
    public CollectionModel<EntityModel<ProductResponse>> getAllProducts(@PathVariable String id) {
        User user = userService.getById(id);
        List<Product> userProducts = productService.getProductsByUser(user);
        List<ProductResponse> productResponses = userProducts.stream().map(ProductResponse::new).toList();
        List<EntityModel<ProductResponse>> productResponsesModel = productResponses.stream().map(productModelAssembler::toModel).toList();
        return CollectionModel.of(
                productResponsesModel,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserControllerImpl.class).getAllProducts(id)).withSelfRel()
        );
    }
}
