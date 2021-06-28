package udodog.goGetterServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import udodog.goGetterServer.controller.interceptor.BkUserApiInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bkUserApiInterceptor())
                .addPathPatterns("/api/bkusers/**");
    }

    @Bean
    public BkUserApiInterceptor bkUserApiInterceptor(){
        return new BkUserApiInterceptor();
    }
}
