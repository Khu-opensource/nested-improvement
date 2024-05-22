package khu.nested.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
public class BlogPost {
    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Nested)
    private List<Tag> tags;

    @Field(type = FieldType.Nested)
    private List<Comment> comments;

    public BlogPost(String title, String author, List<Tag> tags, List<Comment> comments) {
        this.title = title;
        this.author = author;
        this.tags = tags;
        this.comments = comments;
    }
    public BlogPost() {
    }

    // Getters and setters
}