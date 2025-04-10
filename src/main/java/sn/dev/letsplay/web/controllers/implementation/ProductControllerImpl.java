package sn.dev.letsplay.web.controllers.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.dev.letsplay.data.entities.Product;
import sn.dev.letsplay.services.CrudService;
import sn.dev.letsplay.services.ProductService;
import sn.dev.letsplay.web.assembler.ProductModelAssembler;
import sn.dev.letsplay.web.dto.requests.ProductRequest;
import sn.dev.letsplay.web.dto.responses.ProductResponse;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductControllerImpl extends AbstractCrudController<ProductResponse, ProductRequest, Product> {

    private final ProductService productService;

    private final ProductModelAssembler productModelAssembler;

    @Override
    protected CrudService<Product> getService() {
        return productService;
    }

    @Override
    protected EntityModel<ProductResponse> mapDtoToModel(ProductResponse obj) {
        return productModelAssembler.toModel(obj);
    }

    @Override
    protected ProductResponse mapPovoToDto(Product obj) {
        return new ProductResponse(obj);
    }

    @Override
    protected Product mapDtoToPovo(ProductRequest obj) {
        return obj.toEntity();
    }
}
