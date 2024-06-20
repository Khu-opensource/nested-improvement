package khu.nested.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Order {
    @Id
    private String id;

    @Field(type = FieldType.Nested)
    public List<Product> products;
}
