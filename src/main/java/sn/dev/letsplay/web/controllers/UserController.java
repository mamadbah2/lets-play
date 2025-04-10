package sn.dev.letsplay.web.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import sn.dev.letsplay.data.entities.User;
import sn.dev.letsplay.web.dto.requests.UserRequest;
import sn.dev.letsplay.web.dto.responses.ProductResponse;

public interface UserController {
    ResponseEntity<String> login(UserRequest userRequest);
    CollectionModel<EntityModel<ProductResponse>> getAllProducts(String userId);
}
