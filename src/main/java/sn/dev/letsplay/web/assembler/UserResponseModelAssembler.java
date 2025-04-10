package sn.dev.letsplay.web.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import sn.dev.letsplay.web.controllers.implementation.UserControllerImpl;
import sn.dev.letsplay.web.dto.responses.UserResponse;

@Component
public class UserResponseModelAssembler implements RepresentationModelAssembler<UserResponse, EntityModel<UserResponse>> {
    @Override
    public EntityModel<UserResponse> toModel(UserResponse user) {
        EntityModel<UserResponse> entityModel = EntityModel.of(
                user,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserControllerImpl.class).getById(user.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserControllerImpl.class).getAll()).withRel("users")
        );
        if ("ROLE_Admin".equalsIgnoreCase(user.getRole())) {
            entityModel.add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserControllerImpl.class).delete(user.getId())).withRel("delete")
            );
        }
        return entityModel;
    }
}
