package club.seaguard.app.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置
 *
 * @author WaTony Weng
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * 上传路径
   */
  @Value("${guard.path.upload}")
  private String uploadPath;

  /**
   * Web应用路径
   */
  @Value("${guard.path.webapp}")
  private String webAppPath;

  /**
   * 静态资源路径
   */
  @Value("${spring.resource.static-locations}")
  private String staticLocations;

  /**
   * 跨域配置
   *
   * @return 跨域过滤器
   */
  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }

  /**
   * 静态资源的配置
   *
   * @param registry 资源处理器
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
      .addResourceLocations("file:" + uploadPath + "//", "file:" + webAppPath + "//")
      .addResourceLocations(staticLocations.split(","));
  }

  /**
   * 访问根路径默认跳转页面
   *
   * @param registry 视图控制处理器
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index.html");
  }

}
