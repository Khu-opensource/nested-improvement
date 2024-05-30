package khu.nested.repository;

import khu.nested.domain.BlogPosts;
import khu.nested.domain.ClassDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<BlogPosts, String> {
}
