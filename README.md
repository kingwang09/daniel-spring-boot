# daniel-spring-boot
freehongs spring-boot version

외부 톰캣에 Springboot 실행 가능함.
내가 제어할 수 없는 곳에서 리소스 접근 필터가 걸려 있는 것으로 추정됨. 그래서 "*.do" 로만 스프링 접근이 가능함.
정상 동작하려면 api suffix를 .do로 구현해줘야함.

내장 톰캣을 사용할 수 없기 때문에..
실행 자체가 되지 않음.
(구동하려고 할 경우, 톰캣 버전 이슈)

- git ssh setting test3
