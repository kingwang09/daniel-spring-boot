package net.freehongs.daniel.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticClientConfig {
    @Value("${spring.elasticsearch.url:localhost}")
    private String elasticsearchUrl;

    @Value("${spring.elasticsearch.port:9200}")
    private Integer elasticsearchPort;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticsearchUrl, elasticsearchPort, "http"))
        );
    }
}
