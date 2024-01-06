package ssamchi.softeer.drivechat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DriveChat API")
                        .description("DriveChat 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}