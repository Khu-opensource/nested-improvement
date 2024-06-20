package khu.nested.controller;

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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ElasticsearchOperations elasticsearchOperations;
    private final OrderRepository orderRepository;

    /**
     * 제품 이름과 가격 범위 검색
     * nested 개선 후
     */
    @GetMapping("/search/product/between/after")
    public ResponseEntity<List<Order>> findBetweenImproved() {
        String productName = "fantastic";
        int price1 = 40;
        int price2 = 30;
        Criteria criteria1 = new Criteria("products.name").is(productName);
        Criteria criteria2 = new Criteria("products.price").greaterThan(price2).lessThan(price1);
        Criteria criteria = new Criteria("products").nest(criteria1, criteria2);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();
        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 가격 범위 검색
     * nested 개선 전
     */
    @GetMapping("/search/product/between/before")
    public ResponseEntity<List<Order>> findBetween() {
        String productName = "fantastic";
        int price1 = 40;
        int price2 = 30;
        Criteria criteria = new Criteria("products.name").is(productName).and("products.price").greaterThan(price2).lessThan(price1);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 가격으로 검색
     * nested 개선 후
     */
    @GetMapping("/search/product/is/after")
    public ResponseEntity<List<Order>> FindIsImproved() {
        String productName = "Synergistic";
        int price = 80;
        Criteria criteria1 = new Criteria("products.name").is(productName);
        Criteria criteria2 = new Criteria("products.price").is(price);
        Criteria criteria = new Criteria("products").nest(criteria1, criteria2);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();
        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 가격으로 검색
     * nested 개선 전
     */
    @GetMapping("/search/product/is/before")
    public ResponseEntity<List<Order>> findSynergistic() {
        String productName = "Synergistic";
        int price = 80;
        Criteria criteria = new Criteria("products.name").is(productName).and("products.price").is(price);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 가격으로 검색
     * ElasticsearchRepository 사용
     */
    @GetMapping("/search/product/name/repository")
    public ResponseEntity<Optional<List<Order>>> findSynergisticRepository() {
        String productName = "Synergistic";
        int price = 80;
        Optional<List<Order>> ordersByProductsNameAndProductsPrice = orderRepository.findOrdersByProductsNameAndProductsPrice(productName, price);

        return ResponseEntity.ok(ordersByProductsNameAndProductsPrice);
    }

    /**
     * 제품 이름과 특정 가격 이상 검색
     * nested 개선 후
     */
    @GetMapping("/search/product/greater/after")
    public ResponseEntity<List<Order>> findGreaterImproved() {
        String productName = "Ergonomic";
        int price = 70;
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

    /**
     * 제품 이름과 특정 가격 이상 검색
     * nested 개선 전
     */
    @GetMapping("/search/product/greater/before")
    public ResponseEntity<List<Order>> findGreater() {
        String productName = "Ergonomic";
        int price = 70;
        Criteria criteria = new Criteria("products.name").is(productName).and("products.price").greaterThan(price);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 특정 가격 이하 검색
     * nested 개선 후
     */
    @GetMapping("/search/product/lesser/after")
    public ResponseEntity<List<Order>> findLesserImproved() {
        String productName = "Ergonomic";
        int price = 70;
        Criteria criteria1 = new Criteria("products.name").is(productName);
        Criteria criteria2 = new Criteria("products.price").lessThan(price);
        Criteria criteria = new Criteria("products").nest(criteria1, criteria2);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();
        return ResponseEntity.ok(collect);
    }

    /**
     * 제품 이름과 특정 가격 이하 검색
     * nested 개선 전
     */
    @GetMapping("/search/product/lesser/before")
    public ResponseEntity<List<Order>> findLesser() {
        String productName = "Ergonomic";
        int price = 70;
        Criteria criteria = new Criteria("products.name").is(productName).and("products.price").lessThan(price);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Order> search = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);
    }

    /**
     * 두 개의 조건에 대해서 검색
     * nested 개선 후
     */
    @GetMapping("/search/product/or/after")
    public ResponseEntity<List<Order>> findOrImproved() {

        Criteria criteria1 = new Criteria("products.name").is("Ergonomic");
        Criteria criteria2 = new Criteria("products.name").is("Heavy Duty Bronze Knife");
        Criteria criteria3 = new Criteria("products.price").lessThan(20);
        Criteria criteria4 = new Criteria("products.price").lessThan(30);

        Criteria firstOption = new Criteria("products").nest(criteria1, criteria3);
        Criteria secondOption = new Criteria("products").nest(criteria2, criteria4);

        Criteria criteria = firstOption.or(secondOption);

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<Order> searchHits = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> results = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();

        return ResponseEntity.ok(results);
    }

    /**
     * 두 개의 조건에 대해서 검색
     * nested 개선 전
     */
    @GetMapping("/search/product/or/before")
    public ResponseEntity<List<Order>> findOr() {
        Criteria criteria1 = new Criteria("products.name").is("Ergonomic")
                .and(new Criteria("products.price").lessThan(20));

        Criteria criteria2 = new Criteria("products.name").is("Heavy Duty Bronze Knife")
                .and(new Criteria("products.price").lessThan(30));

        Criteria orCriteria = criteria1.or(criteria2);

        CriteriaQuery criteriaQuery = new CriteriaQuery(orCriteria);

        SearchHits<Order> searchHits = elasticsearchOperations.search(criteriaQuery, Order.class);

        List<Order> results = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();

        return ResponseEntity.ok(results);
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateData() {
        int numDocs = 1000;
        List<Order> orders = generateNestedData(numDocs);
        orderRepository.saveAll(orders);
        return ResponseEntity.ok("1000개의 데이터 생성");
    }

    public static List<Order> generateNestedData(int numDocs) {
        Faker faker = new Faker();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numDocs; i++) {
            List<Product> products = new ArrayList<>();
            int productCount = faker.number().numberBetween(1, 5);
            for (int j = 0; j < productCount; j++) {
                Product product = Product.builder()
                        .name(faker.commerce().productName())
                        .price(faker.number().numberBetween(10, 100))
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
