package net.freehongs.daniel.domain.elastic.repository;


import net.freehongs.daniel.domain.elastic.model.ElasticExample;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ElasticExampleRepository extends PagingAndSortingRepository<ElasticExample, String> {
}
