package khu.nested.controller;

import khu.nested.domain.*;
import khu.nested.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final ElasticsearchOperations elasticsearchOperations;
    private final BlogRepository blogRepository;

    @GetMapping("/blog")
    public ResponseEntity<List<BlogPosts>> findPosts(){
        String tag1 = "Elasticsearch";
        String name = "Alice";

        Criteria criteria1 = new Criteria("blogPosts.comments.user").is("Alice");
        Criteria criteria2 = new Criteria("blogPosts.comments.context").is("NOT Very");
        Criteria criteria = new Criteria("blogPosts.comments").nest(criteria1, criteria2);

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<BlogPosts> search = elasticsearchOperations.search(query, BlogPosts.class);
        System.out.println("search = " + search);
        List<BlogPosts> list = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/blog/v2")
    public ResponseEntity<List<BlogPosts>> findPostsV2(){
        Criteria criteria = new Criteria("blogPosts.comments.user").is("Alice").is("blogPosts.comments.context").is("Not Very");

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<BlogPosts> search = elasticsearchOperations.search(query, BlogPosts.class);
        System.out.println("search = " + search);
        List<BlogPosts> list = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/title")
    public ResponseEntity<List<BlogPosts>> findTitle(){
        Criteria criteria1 = new Criteria("blogPosts.author").is("Jane");
        Criteria criteria2 = new Criteria("blogPosts.title").is("Elasticsearch");
        Criteria criteria = new Criteria("blogPosts").nest(criteria1, criteria2);

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<BlogPosts> search = elasticsearchOperations.search(query, BlogPosts.class);
        System.out.println("search = " + search);
        List<BlogPosts> list = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/title/v2")
    public ResponseEntity<List<BlogPosts>> findTitleV2(){
        Criteria criteria = new Criteria("blogPosts.author").is("Jane").is("blogPosts.title").is("Elasticsearch");

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<BlogPosts> search = elasticsearchOperations.search(query, BlogPosts.class);
        System.out.println("search = " + search);
        List<BlogPosts> list = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
        return ResponseEntity.ok(list);
    }


    @PostMapping("/blog/generate")
    public ResponseEntity<?> generateData(){
        int numDocs = 1000;
        List<BlogPosts> blogPosts = generateNestedData(numDocs);
        blogRepository.saveAll(blogPosts);
        return ResponseEntity.ok("1000개의 데이터 생성");
    }

    public static List<BlogPosts> generateNestedData(int numDocs){
        Faker faker = new Faker();
        List<BlogPosts> blogPostsList = new ArrayList<>();
        for (int i = 0; i < numDocs; i++) {
            List<BlogPost> blogPostList = new ArrayList<>();
            int numBlogPosts = faker.number().numberBetween(1, 10);
            for (int j = 0; j < numBlogPosts; j++) {
                List<Tag> tags = new ArrayList<>();
                int tagCount = faker.number().numberBetween(1,5);
                for (int k = 0; k < tagCount; k++) {
                    tags.add(new Tag(faker.lorem().word()));
                }
                List<Comment> comments = new ArrayList<>();
                int commentCount = faker.number().numberBetween(2,8);
                for (int l = 0; l < commentCount; l++) { // Assuming each blog post has 10 comments
                    comments.add(new Comment(faker.name().fullName(), faker.lorem().sentence()));
                }
                BlogPost blogPost = BlogPost.builder()
                        .title(faker.book().title())
                        .author(faker.book().author())
                        .tags(tags)
                        .comments(comments)
                        .build();
                blogPostList.add(blogPost);
            }
            BlogPosts blogPosts = BlogPosts.builder()
                    .blogPosts(blogPostList)
                    .build();
            blogPostsList.add(blogPosts);
        }
        return blogPostsList;
    }

}
