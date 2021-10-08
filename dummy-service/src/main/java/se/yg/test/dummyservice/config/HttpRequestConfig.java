package se.yg.test.dummyservice.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.yg.test.dummyservice.filter.RequestFilter;

import java.util.Arrays;

@Configuration
public class HttpRequestConfig {

    @Bean
    public FilterRegistrationBean reReadableRequestFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new RequestFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*")); return filterRegistrationBean;
    }


}
