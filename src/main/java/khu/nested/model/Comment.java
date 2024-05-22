package khu.nested.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
public class Comment {
    @Field(type = FieldType.Text)
    private String user;

    @Field(type = FieldType.Text)
    private String content;

    public Comment(String user, String content) {
        this.user = user;
        this.content = content;
    }

    public Comment() {
    }

    // Getters and setters
}