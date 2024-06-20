package khu.nested.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Field(type = FieldType.Text)
    private String user;

    @Field(type = FieldType.Text)
    private String context;
}
