# spring boot 애플리케이션
spring boot, spring security, thymeleaf, h2, mybatis 등을 활용한 샘플 애플리케이션이다.

## 프로젝트 정의
### 프로젝트 설정
- spring boot : 3.3.13
- name : app1-thymeleaf
- type : maven
- packaging : jar
- java version : 21
- language : java
- group : com.example
- artifact : app1-thymeleaf

### 의존성
#### spring-boot-starter 의존성
- web
- spring security
- thymeleaf
- h2
- mybatis
- validation
- lombok
- devTools
#### 기타 의존성
- thymeleaf-layout-dialect
- modelmapper
- commonmark, commonmark-ext-gfm-tables, commonmark-ext-image-attributes (마크다운 지원 라이브러리)

## 프로젝트 구조
```pgsql
app1-thymeleaf
  ├── src/main/java/
  └── src/main/resources/
    └── db/
      └── h2/
        ├── schema.sql               # 테이블 스키마 정의, 애플리케이션 실행시 테이블을 생성한다.
        └── data.sql                 # 초기 데이터 정의, 애플리케이션 실행시 초기 데이터를 저장한다.
    ├── mapper/                      # mybatis SQL 매퍼 파일
    ├── static                       # 정적 HTML 컨텐츠 폴더
    └── templates/                   # thymeleaf 뷰 템플릿 폴더
      ├── fragments/                 # thymeleaf fragment 폴더, 여러 뷰 템플릿에서 공통으로 사용되는 뷰 템플릿
      ├── layout/                    # thymeleaf 레이아웃 뷰 템플릿 폴더
      ├── posts/                     # 게시글 관련 뷰 템플릿 폴더
      ├── index.html                 # 홈 화면 뷰 템플릿
      ├── error-page.html            # 오류 화면 뷰 템플릿
      ├── login.html                 # 로그인 화면 뷰 템플릿
      └── signup.html                # 회원가입 화면 뷰 템플릿
    └── application.properties       # spring boot 환경설정 파일
```

## 화면 구성하기
### 레이아웃 화면 작성하기
`templates/layout`폴더에 `base.html`을 작성한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
  xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>샘플 애플리케이션</title>
  <!-- bootstrap css -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap icon css -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

  <!-- style 레이아웃 fragment로 대체된다 -->
  <th:block layout:fragment="style"></th:block>
</head>
<body>
  <!-- 내비바 fragment로 대체된다 -->
  <nav th:replace="~{fragments/navbar :: navbar-fragment}"></nav>

  <div class="container mt-4">
    <!-- cotent 레이아웃 fragment로 대체된다 -->
    <th:block layout:fragment="content"></th:block>
  </div>

  <!-- boostrap javascript 라이브러리 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <!-- script 레이아웃 fragment로 대체된다 -->
  <th:block layout:fragment="script"></th:block>
</body>
</html>
```

### 내비바 fragment를 작성한다.
`templates/fragments`폴더에 `navbar.html`을 작성한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
  xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<nav th:fragment="navbar-fragment" class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">스프링부트 게시판</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-support-content"
      aria-controls="navbar-support-content" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar-support-content">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link"
             th:classappend="${#strings.equals(menu, 'home')} ? 'active fw-bold'"
             th:href="@{/}">홈</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" 
             th:classappend="${#strings.equals(menu, 'posts')} ? 'active fw-bold'"					   
             th:href="@{/posts/list}">게시글</a>
        </li>
        <!-- 코드 생략 -->
      </ul>
    </div>
  <div>
</nav>
</html>
```

### 뷰 템플릿 작성하기
기본 레이아웃(`templates/layout/base.html`)을 이용해서 뷰 템플릿을 아래와 같은 형식으로 작성한다.
```html
<!DOCTYPE html>
<!--
  layout:decorate="~{layout/base}"
    - 현재 뷰 템플릿 파일이 layout/base.html 템플릿을 기반(layout 템플릿) 으로 삼아 확장되도록 한다.
  th:with="menu='home'"
    - 현재 뷰 템플릿에서 사용할 지 변수를 선언한다.
    - 여기서 설정한 값은 base.html, navbar.html 등에서 사용할 수 있다.
    - navbar.html에서 위의 설정값을 활용해서 내비바의 특정 메뉴를 강조한다.
-->
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
      layout:decorate="~{layout/base}"
      th:with="menu='home'">

<!-- base.html의 <th:block layout:fragment="style"></th:block> 자리에 대체된다. -->
<style layout:fragment="style">
  /* 해당 뷰 템플릿 화면에 적용할 CSS를 작성한다 */
</style>

<!-- base.html의 <th:block layout:fragment="content"></th:block> 자리에 대체된다. -->
<section layout:fragment="content">
  <!-- 해당 뷰 템플릿 화면의 컨텐츠를 작성한다 -->
</section>

<!-- base.html의 <th:block layout:fragment="script"></th:block> 자리에 대체된다. -->
<script layout:fragment="script" type="text/javascript">
  /* 해당 뷰 템플릿 화면에 적용되는 javascript 코드를 작성한다 */
</script>
</html>
```







