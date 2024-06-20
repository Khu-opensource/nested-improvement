package khu.nested.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {
    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Nested)
    private List<Tag> tags;

    @Field(type = FieldType.Nested)
    private List<Comment> comments;
}
