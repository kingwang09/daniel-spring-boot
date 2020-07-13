package net.freehongs.daniel;

import lombok.extern.slf4j.Slf4j;
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

  @GetMapping("/index.do")
  public String index(){
    return "daniel spring boot world!!";
  }

  private static final Map<String, Object> DEFAULT_PROPS = new HashMap<>();

  static{
    DEFAULT_PROPS.put("spring.application.name", "daniel");
    // 테스트용 일 때 hot reload 설정 기본값으로 지정
    DEFAULT_PROPS.put("spring.devtools.restart.enabled", "true");
    // 설정은 config.name에 설정된 순서대로 가장 앞쪽부터 뒤쪽으로 override됩니다.
    DEFAULT_PROPS.put("spring.config.name", "${spring.application.name}-local");
    // 설정 위치는 classpath:./ classpath:config/ 가 기본이고 지정된 모든 경로를 다 확인합니다.
    DEFAULT_PROPS.put("spring.config.location", "classpath:/,../config/");
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    log.debug("prop: {}", DEFAULT_PROPS);
    return builder.properties(DEFAULT_PROPS).sources(DanielGradleApplication.class);
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder().properties(DEFAULT_PROPS).sources(DanielGradleApplication.class).run(args);
  }
}


