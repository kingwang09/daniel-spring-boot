package net.freehongs.daniel.domain.elastic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.elastic.model.ElasticExample;
import net.freehongs.daniel.domain.elastic.repository.ElasticExampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/elastic")
public class ElasticRestController {
    private final ElasticExampleRepository repository;

    @GetMapping("/all")
    public List<ElasticExample> getAll(){
        log.debug("elastic getAll");
        Page<ElasticExample> result = repository.findAll(PageRequest.of(0, 10));
        log.debug("total Count: {}", result.getTotalElements());
        return result.getContent();
    }

    @GetMapping
    public ElasticExample index(@RequestParam String name, @RequestParam String value){
        log.debug("elastic index: name={}, value={}", name, value);
        return repository.save(ElasticExample.builder()
                    .name(name).value(value)
                .build());
    }
}
