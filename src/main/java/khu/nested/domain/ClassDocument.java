package khu.nested.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.List;

@Document(indexName = "class")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ClassDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Nested)
    private List<Name> names;
}
