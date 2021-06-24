package udodog.goGetterServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());


    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("우도독 프로젝트 API 문서")
                .version("1.0")
                .description(
                        "<h2> API 변경사항이 있으면 즉시 팀원들에게 보고하기! </h2> \t\n\t\n" +
                        "/api -> 블랙회원, 일반회원, 관리자 사용 가능 API \t\n\t\n" +
                        "/userapi -> 일반회원, 관리자 사용 가능 API \t\n\t\n" +
                        "/admapi -> 관리자 \t\n\t\n" +
                        "해당 URI로 시작하는 API들은 헤더에 토큰을 포함해서 요청."

                )
                .build();
    }


}
