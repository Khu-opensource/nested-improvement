package khu.nested.repository;

import khu.nested.domain.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends ElasticsearchRepository<Order, String> {

    Optional<List<Order>> findOrdersByProductsNameAndProductsPrice(String name, int price);
}
