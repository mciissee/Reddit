package fr.uge.jee.reddit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://www.baeldung.com/spring-rest-openapi-documentation

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI redditOpenApi() {
        return new OpenAPI()
            .info(
                new Info()
                .title("Reddit API")
                .description("Reddit application rest api documentation.")
                .version("v1.0.0")
            );
    }
}
