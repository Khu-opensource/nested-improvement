package khu.nested.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
public class Tag {
    @Field(type = FieldType.Text)
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag() {
    }


    // Getters and setters
}
