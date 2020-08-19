package GraphQLDemo.GraphQLDemo;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import GraphQLDemo.GraphQLDemo.queries.VehicleMutation;
import GraphQLDemo.GraphQLDemo.queries.VehicleQuery;

@SpringBootApplication
public class GraphQlDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GraphQlDemoApplication.class, args);
	}
}
