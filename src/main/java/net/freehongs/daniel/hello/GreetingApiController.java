package net.freehongs.daniel.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.hello.model.Hello;
import net.freehongs.daniel.domain.hello.repository.HelloRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class GreetingApiController
 * @Description
 * @Author hyungeun.jin
 * @Since 2020. 7. 3.
 * @Version 1.0
 * @COPYRIGHT © WADIZ ALL RIGHTS RESERVED.
 * ------------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 수정일 || 수정자 || 수정내용
 * ------------------------------------------------------------------------
 * 2020. 7. 3. || 진형은 || 최초생성
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/greeting/")
public class GreetingApiController {

  private final HelloRepository repository;

  @GetMapping("/hello.do")
  public Hello hello(@RequestParam String name){
    log.info("say hello : {}", name);
    return repository.save(Hello.builder()
            .name(name)
            .build());
  }
}
