package kr.cornerstone.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import kr.cornerstone.global.enums.ResponseType;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi cloudApi(){
        Info info = new Info()
                .title("코너스톤_API_[V1]")
                .version("v1")
                .description(makeResponseCodes());

        OpenApiCustomizer openApiCustomizer = (openAPI) -> openAPI.info(info);

        String[] paths = {"/auth/**", "/api/v1/**"};

        return GroupedOpenApi.builder().group("Corner Stone API_v1").pathsToMatch(paths).addOpenApiCustomizer(openApiCustomizer)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer")));
    }


    /*통합에러코드 정의*/
    public String makeResponseCodes(){

        StringBuilder sb = new StringBuilder();
        sb.append("### [ 통합 상태코드 정리 ]\r\n");
        sb.append("|코드|정보|메세지|\r\n");
        sb.append("|----|-------|---------------|\r\n");
        Arrays.stream(ResponseType.values())
                .map(responseType -> "|" + responseType.getStatusCode() + "|" + responseType.getSummary() + "|" + responseType.getMessage() + "| \r\n")
                .forEach(x -> sb.append(x));

        return sb.toString();
    }
}
