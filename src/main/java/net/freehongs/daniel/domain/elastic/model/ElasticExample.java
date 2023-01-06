package net.freehongs.daniel.domain.elastic.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "spring-data-elastic-example")
public class ElasticExample {
    @Id
    private String id;

    private String name;

    private String value;
}
