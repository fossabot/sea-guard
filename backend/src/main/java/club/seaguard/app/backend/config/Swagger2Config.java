package club.seaguard.app.backend.config;

import club.seaguard.app.backend.common.constant.AppConstant;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author WaTony Weng
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 */

@Slf4j
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config implements WebMvcConfigurer {

  /**
   * 资源过滤器
   *
   * @param registry 资源处理器
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * 创建API文档清单
   *
   * @return 文档清单
   */
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
      .apis(RequestHandlerSelectors.basePackage("club.seaguard.app.backend.modules"))
      .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
      .build().globalOperationParameters(setHeaderToken());
  }

  /**
   * 设置Header认证码
   *
   * @return 参数集合
   */
  private List<Parameter> setHeaderToken() {
    ParameterBuilder tokenParam = new ParameterBuilder();
    List<Parameter> parameters = new ArrayList<>();
    tokenParam.name(AppConstant.X_ACCESS_TOKEN).description("token").modelRef(new ModelRef("string"))
      .parameterType("header").required(false).build();
    parameters.add(tokenParam.build());
    return parameters;
  }

  /**
   * 获取API信息
   *
   * @return API信息
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("SeaGuard 后台服务API接口文档").version("1.0.0").description("RestFul 风格接口").build();
  }

}
