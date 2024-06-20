package khu.nested.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private int price;
}

