package khu.nested.repository;

import khu.nested.domain.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, String> {
}
