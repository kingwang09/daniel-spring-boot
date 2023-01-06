package net.freehongs.daniel.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


@Configuration
public class SpringDataElasticConfig extends AbstractElasticsearchConfiguration {
    @Value("${spring.elasticsearch.url:localhost}")
    private String elasticsearchUrl;

    @Value("${spring.elasticsearch.port:9200}")
    private Integer elasticsearchPort;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration singleConfiguration = ClientConfiguration.create(elasticsearchUrl+":"+elasticsearchPort);
        return RestClients.create(singleConfiguration).rest();
    }
}
