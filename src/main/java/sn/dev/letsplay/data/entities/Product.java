package sn.dev.letsplay.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("Product")
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    @Field(write = Field.Write.ALWAYS)
    private String description;
    private double price;
    private String userId;
}
