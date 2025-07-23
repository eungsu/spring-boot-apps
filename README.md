# Spring Boot 샘플 애플리케이션
Sprign Boot를 활용한 샘플 애플리케이션들이다.

## 샘플 애플리케이션 목록
### `app1-backend` 애플리케이션
RESTful 웹 애플리케이션이다.

- 사용된 의존성
  - `spring boot`
  - `spring security`
  - `validation`
  - `h2`
  - `mybatis`
  - `lombok`
  - `modelmapper`
  - `devtools`
  - `jjwt-api`, `jjwt-impl`, `jjwt-jackson` - jwt을 지원하는 라이브러리
 
### `app1-frontend-react` 애플리케이션
react로 작성된 프론트엔드 애플리케이션이다.  
`app1-backend`와 연동되어 동작한다.

- 사용된 의존성
  - axios
  - react-router-dom
  - bootstrap
  - bootstrap-icon

### `app2-thymeleaf` 애플리케이션
thymeleaf를 뷰 템플릿으로 사용하는 웹 애플리케이션이다.

- 사용된 의존성
  - `spring boot`
  - `spring security`
  - `validation`
  - `thymeleaf`, `thymeleaf layout`
  - `h2`
  - `mybatis`
  - `lombok`
  - `modelmapper`
  - `devtools`
  - `commonmark`, `commonmark-ext-gfm-tables`, `commonmark-ext-image-attributes` - markdown을 지원하는 라이브러리
 
 ### `app3-jsp` 애플리케이션
jsp를 뷰 템플릿으로 사용하는 웹 애플리케이션이다.

- 사용된 의존성
  - `spring boot`
  - `spring security`, `spring-security-taglibs`
  - `validation`
  - `tomcat-embed-jasper`, `jakarta.servlet-api`, `jakarta.servlet.jsp.jstl-api`, `jakarta.servlet.jsp.jstl`
  - `h2`
  - `mybatis`
  - `lombok`
  - `modelmapper`
  - `devtools`
  - `commonmark`, `commonmark-ext-gfm-tables`, `commonmark-ext-image-attributes` - markdown을 지원하는 라이브러리
