package br.com.amandacampos.starwarsplanets.configurations;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
