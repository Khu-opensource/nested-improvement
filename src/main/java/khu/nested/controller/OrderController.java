package khu.nested.controller;

import khu.nested.domain.ClassDocument;
import khu.nested.domain.Order;
import khu.nested.domain.Product;
import khu.nested.repository.OrderRepository;
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
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ElasticsearchOperations elasticsearchOperations;
    private final OrderRepository orderRepository;

    @GetMapping("/search/product")
    public ResponseEntity<List<Order>> findOrder(){
        String productName = "Synergistic";
        int price = 20;
        Criteria criteria1 = new Criteria("products.name").is(productName);
        Criteria criteria2 = new Criteria("products.price").greaterThan(price);
        Criteria criteria = new Criteria("products").nest(criteria1, criteria2);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);

    }

    @GetMapping("/search/productV2")
    public ResponseEntity<List<Order>> findOrder2(){
        String productName = "Synergistic";
        int price = 20;
        Criteria criteria = new Criteria("products.name").is(productName).and("products.price").greaterThan(price);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);

    }



    @PostMapping("/generate")
    public ResponseEntity<?> generateData(){
        int numDocs = 1000;
        List<Order> orders = generateNestedData(numDocs);
        orderRepository.saveAll(orders);
        return ResponseEntity.ok("1000개의 데이터 생성");
    }

    public static List<Order> generateNestedData(int numDocs){
        Faker faker = new Faker();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numDocs; i++) {
            List<Product> products = new ArrayList<>();
            int productCount = faker.number().numberBetween(1,5);
            for (int j = 0; j < productCount; j++) {
                Product product = Product.builder()
                        .name(faker.commerce().productName())
                        .price(faker.number().numberBetween(10,100))
                        .build();
                products.add(product);
            }
            Order order = Order.builder()
                    .products(products)
                    .build();
            orders.add(order);
        }
        return orders;
    }


}
