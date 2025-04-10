package sn.dev.letsplay.web.dto.responses;

import lombok.Data;
import sn.dev.letsplay.data.entities.Product;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private String userId;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.userId= product.getUserId();
    }
}
