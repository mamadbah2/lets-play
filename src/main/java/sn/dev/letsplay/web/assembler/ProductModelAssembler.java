package sn.dev.letsplay.web.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import sn.dev.letsplay.web.controllers.implementation.ProductControllerImpl;
import sn.dev.letsplay.web.dto.responses.ProductResponse;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {
    @Override
    public EntityModel<ProductResponse> toModel(ProductResponse product) {
        return EntityModel.of(
                product,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductControllerImpl.class).getById(product.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductControllerImpl.class).getAll()).withRel("products")
        );
    }
}
