package khu.nested.controller;

import khu.nested.domain.ClassDocument;
import khu.nested.domain.Order;
import khu.nested.domain.Product;
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
public class ClassController {
    private final ElasticsearchOperations elasticsearchOperations;
    private final BlogRepository classRepository;

    @GetMapping("/class")
    public ResponseEntity<?> findClass(){
        String firstName = "김";
        String lastName = "철수";

        Criteria criteria1 = new Criteria("names.firstName").is(firstName);
        Criteria criteria2 = new Criteria("names.lastName").is(lastName);

        Criteria criteria = new Criteria("names").nest(criteria1, criteria2);

        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<ClassDocument> search = elasticsearchOperations.search(query, ClassDocument.class);

        List<ClassDocument> collect = search.getSearchHits().stream().map(
                SearchHit::getContent
        ).toList();

        return ResponseEntity.ok(collect);
    }



}
