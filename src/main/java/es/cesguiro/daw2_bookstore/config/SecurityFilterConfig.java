package es.cesguiro.daw2_bookstore.config;

import es.cesguiro.daw2_bookstore.filter.TokenAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityFilterConfig {

    // --- ORDEN 1: LoginFilter ---
    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenAuthenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<TokenAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAuthenticationFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }
}
