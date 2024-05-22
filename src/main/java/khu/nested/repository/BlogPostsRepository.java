package khu.nested.repository;

import khu.nested.model.BlogPosts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogPostsRepository extends ElasticsearchRepository<BlogPosts, String> {
}
