package khu.nested;

import khu.nested.model.BlogPost;
import khu.nested.model.BlogPosts;
import khu.nested.model.Comment;
import khu.nested.model.Tag;
import khu.nested.repository.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NestedApplication implements CommandLineRunner {

	private final BlogPostsRepository blogPostsRepository;

	public NestedApplication(BlogPostsRepository blogPostsRepository) {
		this.blogPostsRepository = blogPostsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(NestedApplication.class, args);
	}

	@Override
	public void run(String... args) {
		blogPostsRepository.deleteAll();
		blogPostsRepository.saveAll(createSampleData());
	}

	private List<BlogPosts> createSampleData() {
		BlogPosts blogPosts1 = new BlogPosts();
		blogPosts1.setId("1");
		blogPosts1.setBlogposts(Arrays.asList(
				new BlogPost("Introduction to Elasticsearch", "John Doe",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Beginner")),
						Arrays.asList(new Comment("Alice", "Very helpful guide."), new Comment("Bob", "Great introduction!"))
				),
				new BlogPost("Advanced Elasticsearch Techniques", "Jane Smith",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Advanced")),
                        List.of(new Comment("Charlie", "Loved the advanced tips!"))
				)
		));

		BlogPosts blogPosts2 = new BlogPosts();
		blogPosts2.setId("2");
		blogPosts2.setBlogposts(Arrays.asList(
				new BlogPost("Getting Started with Spring Data", "Jane Doe",
						Arrays.asList(new Tag("Spring"), new Tag("Data")),
						Arrays.asList(new Comment("Alice", "Clear and concise."), new Comment("David", "Helped me a lot!"))
				),
				new BlogPost("Spring Data Elasticsearch", "John Smith",
						Arrays.asList(new Tag("Spring"), new Tag("Elasticsearch")),
                        List.of(new Comment("Eve", "Very useful information."))
				)
		));

		BlogPosts blogPosts3 = new BlogPosts();
		blogPosts3.setId("3");
		blogPosts3.setBlogposts(Arrays.asList(
				new BlogPost("Mastering Nested Queries", "Alice Johnson",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Nested Queries")),
						Arrays.asList(new Comment("Frank", "Great details on nested queries."), new Comment("Grace", "Exactly what I needed!"))
				),
				new BlogPost("Efficient Data Modeling", "David Brown",
						Arrays.asList(new Tag("Data Modeling"), new Tag("Elasticsearch")),
                        List.of(new Comment("Henry", "Excellent tips on data modeling."))
				)
		));

		BlogPosts blogPosts4 = new BlogPosts();
		blogPosts4.setId("4");
		blogPosts4.setBlogposts(Arrays.asList(
				new BlogPost("Elasticsearch Performance Tuning", "Charlie Green",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Performance")),
                        List.of(new Comment("Isaac", "Really improved my query speeds."))
				),
				new BlogPost("Optimizing Search Queries", "Grace Lee",
						Arrays.asList(new Tag("Search"), new Tag("Optimization")),
                        List.of(new Comment("Jack", "Very useful for my projects."))
				)
		));

		BlogPosts blogPosts5 = new BlogPosts();
		blogPosts5.setId("5");
		blogPosts5.setBlogposts(Arrays.asList(
				new BlogPost("Scaling Elasticsearch Clusters", "David Brown",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Scaling")),
                        List.of(new Comment("Karen", "Great insights on scaling."))
				),
				new BlogPost("Real-time Data Processing", "Alice Johnson",
						Arrays.asList(new Tag("Real-time"), new Tag("Elasticsearch")),
                        List.of(new Comment("Laura", "Very informative article."))
				)
		));

		BlogPosts blogPosts6 = new BlogPosts();
		blogPosts6.setId("6");
		blogPosts6.setBlogposts(Arrays.asList(
				new BlogPost("Understanding Elasticsearch Aggregations", "John Doe",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Aggregations")),
						Arrays.asList(new Comment("Mike", "Explained very well."), new Comment("Nina", "Helped me understand aggregations better."))
				),
				new BlogPost("Elasticsearch Index Management", "Jane Smith",
						Arrays.asList(new Tag("Indexing"), new Tag("Elasticsearch")),
                        List.of(new Comment("Oscar", "Great tips on index management."))
				)
		));

		BlogPosts blogPosts7 = new BlogPosts();
		blogPosts7.setId("7");
		blogPosts7.setBlogposts(Arrays.asList(
				new BlogPost("Full-text Search in Elasticsearch", "Alice Johnson",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Full-text Search")),
						Arrays.asList(new Comment("Paul", "Very detailed and informative."), new Comment("Quinn", "Helped me a lot with my project."))
				),
				new BlogPost("Elasticsearch Security Best Practices", "David Brown",
						Arrays.asList(new Tag("Security"), new Tag("Elasticsearch")),
                        List.of(new Comment("Rachel", "Essential tips for securing Elasticsearch."))
				)
		));

		BlogPosts blogPosts8 = new BlogPosts();
		blogPosts8.setId("8");
		blogPosts8.setBlogposts(Arrays.asList(
				new BlogPost("Elasticsearch for Big Data", "Charlie Green",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Big Data")),
                        List.of(new Comment("Steve", "Very helpful for my big data projects."))
				),
				new BlogPost("Analyzing Logs with Elasticsearch", "Grace Lee",
						Arrays.asList(new Tag("Logs"), new Tag("Elasticsearch")),
                        List.of(new Comment("Tom", "Great article on log analysis."))
				)
		));

		BlogPosts blogPosts9 = new BlogPosts();
		blogPosts9.setId("9");
		blogPosts9.setBlogposts(Arrays.asList(
				new BlogPost("Elasticsearch Query DSL", "Jane Doe",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Query DSL")),
						Arrays.asList(new Comment("Uma", "Very clear explanation of Query DSL."), new Comment("Victor", "Helped me understand complex queries."))
				),
				new BlogPost("Using Kibana with Elasticsearch", "John Smith",
						Arrays.asList(new Tag("Kibana"), new Tag("Elasticsearch")),
                        List.of(new Comment("Wendy", "Great guide on using Kibana."))
				)
		));

		BlogPosts blogPosts10 = new BlogPosts();
		blogPosts10.setId("10");
		blogPosts10.setBlogposts(Arrays.asList(
				new BlogPost("Elasticsearch Scripting", "Alice Johnson",
						Arrays.asList(new Tag("Elasticsearch"), new Tag("Scripting")),
                        List.of(new Comment("Xavier", "Very useful for my custom scripts."))
				),
				new BlogPost("Elasticsearch and Machine Learning", "David Brown",
						Arrays.asList(new Tag("Machine Learning"), new Tag("Elasticsearch")),
                        List.of(new Comment("Yvonne", "Great insights on using ML with Elasticsearch."))
				)
		));

		return Arrays.asList(blogPosts1, blogPosts2, blogPosts3, blogPosts4, blogPosts5, blogPosts6, blogPosts7, blogPosts8, blogPosts9, blogPosts10);
	}
}

