package sn.dev.letsplay.web.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import sn.dev.letsplay.data.entities.Product;

@Data
public class ProductRequest {
    private String id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @Min(value = 0, message = "price cannot be negative")
    private double price;
    @NotBlank(message = "userId cannot be blank")
    private String userId;

    public Product toEntity() {
        Product p = new Product();
        p.setDescription(this.description);
        p.setName(this.name);
        p.setPrice(this.price);
        p.setUserId(this.userId);
        return p;
    }
}
