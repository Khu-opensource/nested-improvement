package khu.nested;

import khu.nested.domain.BlogPosts;
import khu.nested.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

@SpringBootTest
public class Test {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * 특정 태그를 가진 문서에서 특정 사용자가 작성한 댓글의 내용 검색
     */
    @org.junit.jupiter.api.Test
    void testFindByTagUserAndCommentContent() {
        Criteria criteria1 = new Criteria("blogPosts.tags.tag").is("Elasticsearch");
        Criteria user = new Criteria("blogPosts.comments.user").is("Alice");
        Criteria context = new Criteria("blogPosts.comments.context").is("helpful");
        Criteria comments = new Criteria("blogPosts.comments").nest(user, context);
        Criteria tags = new Criteria("blogPosts.tags").nest(criteria1);
        Criteria criteria = new Criteria().and(comments, tags);

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<BlogPosts> search = elasticsearchOperations.search(criteriaQuery, BlogPosts.class);
        List<BlogPosts> collect = search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();

        Assertions.assertThat(collect.size()).isEqualTo(1);

    }

    /**
     * 특정 태그를 가진 문서에서 특정 사용자가 작성한 댓글이 있는 경우, 특정 사용자가 작성한 댓글이 없는 문서 찾기
     */

    @org.junit.jupiter.api.Test
    void testFindByTagUserWithExclusion() {
        Criteria tag = new Criteria("blogPosts.tags.tag").is("Beginner");
        Criteria tagCriteria = new Criteria("blogPosts.tags").nest(tag);
        Criteria comment1 = new Criteria("blogPosts.comments.user").is("Alice");
        Criteria comments2 = new Criteria("blogPosts.comments.user").is("NOT Charlie");

        Criteria comments = new Criteria("blogPosts.comments").nest(comment1, comments2);

        Criteria criteria = new Criteria().and(tagCriteria, comments);

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        List<BlogPosts> results = elasticsearchOperations.search(criteriaQuery, BlogPosts.class).getSearchHits().stream().map(SearchHit::getContent).toList();
        Assertions.assertThat(results.size()).isEqualTo(1);

    }

    /**
     * 여러 태그를 가진 문서 찾기
     */
    @org.junit.jupiter.api.Test
    void testFindByMultipleTags() {
        Criteria tag1 = new Criteria("blogPosts.tags.tag").is("Elasticsearch");
        Criteria tag2 = new Criteria("blogPosts.tags.tag").is("Advanced");

        Criteria criteria = new Criteria("blogPosts.tags").nest(tag1, tag2);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        List<BlogPosts> results = elasticsearchOperations.search(criteriaQuery, BlogPosts.class).getSearchHits().stream().map(SearchHit::getContent).toList();
        Assertions.assertThat(results.size()).isEqualTo(0);
    }

    /**
     * 댓글이 없는 문서 찾기
     */
    @org.junit.jupiter.api.Test
    void testFindWithoutComments() {
        Criteria criteria = new Criteria("blogPosts.comments").empty();

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        List<BlogPosts> results = elasticsearchOperations.search(criteriaQuery, BlogPosts.class).getSearchHits().stream().map(SearchHit::getContent).toList();
        Assertions.assertThat(results.size()).isEqualTo(0);
    }

    /**
     * 특정 태그와 댓글 작성자의 조합으로 문서 찾기
     */
    @org.junit.jupiter.api.Test
    void testFindByTagAndUser() {
        Criteria tag = new Criteria("blogPosts.tags.tag").is("Elasticsearch");
        Criteria nestTag = new Criteria("blogPosts.tags").nest(tag);
        Criteria comment = new Criteria("blogPosts.comments.user").is("Alice");
        Criteria nestComment = new Criteria("blogPosts.comments").nest(comment);
        Criteria criteria = new Criteria("blogPosts").nest(nestComment, nestTag);

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<BlogPosts> search = elasticsearchOperations.search(criteriaQuery, BlogPosts.class);

        List<BlogPosts> collect = search.getSearchHits().stream().map(SearchHit::getContent).toList();
        Assertions.assertThat(collect.size()).isEqualTo(1);
    }

    @org.junit.jupiter.api.Test
    void CurrentTestFindByTagAndUser() {
        Criteria criteria = new Criteria("blogPosts.tags.tag").is("Elasticsearch").and("blogPosts.comments.user").is("Alice");
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        List<BlogPosts> results = elasticsearchOperations.search(criteriaQuery, BlogPosts.class).getSearchHits().stream().map(SearchHit::getContent).toList();
        Assertions.assertThat(results.size()).isEqualTo(1);
    }

}
