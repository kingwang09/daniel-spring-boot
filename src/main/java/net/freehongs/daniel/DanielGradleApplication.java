package net.freehongs.daniel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@SpringBootApplication
public class DanielGradleApplication extends SpringBootServletInitializer {

  @GetMapping("")
  public String index(){
    return "daniel spring boot world!!";
  }

  public static void main(String[] args) {
    SpringApplication.run(DanielGradleApplication.class, args);
  }
}


