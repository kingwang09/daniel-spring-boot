package net.freehongs.danielGradle.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Slf4j
@RestController
@RequestMapping("/api/v1/greeting/")
public class GreetingApiController {

  @GetMapping("/hello.do")
  public String hello(){
    log.info("say hello");
    return "hello springboot world";
  }
}
