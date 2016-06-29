package sportszer.api.events;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("sportszer.api.events")
public class EventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}

	@Bean
	public Docket eventsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("events-api")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/v1/events.*"))
				.build(); 
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Sportszer Events API")
				.version("1.0")
				.build();
	}

}
