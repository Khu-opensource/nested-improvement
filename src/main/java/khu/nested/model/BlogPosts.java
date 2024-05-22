package khu.nested.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
public class BlogPosts {
    @Id
    private String id;

    @Field(type = FieldType.Nested)
    private List<BlogPost> blogposts;

    public BlogPosts(String id, List<BlogPost> blogposts) {
        this.id = id;
        this.blogposts = blogposts;
    }

    public BlogPosts() {
    }

    // Getters and setters
}
