# 백엔드 애플리케이션

## 프로젝트 구조
```arduino
src/
└── main/
    ├── java/
    │   └── com/
    │       └── example/
    │           └── demo/
    │               ├── config                    # 설정 클래스 패키지
    │               ├── exception                 # 예외클래스 패키지
    │               ├── mapper                    # mybatis 매퍼 인터페이스 패키지
    │               ├── service                   # 비즈니스 로직 패키지
    │               ├── util                      # 공통 유틸리티 패키지
    │               ├── vo                        # value object 패키지
    │               └── web                       
    │                   ├── advice                # 예외처리를 지원하는 어드바이스 패키지
    │                   ├── controller            # REST API를 제공하는 컨트롤러 패키지
    │                   ├── request               # JSON 요청을 표현하는 클래스가 포함된 패키지
    │                   ├── response              # JSON 응답을 표현하는 클래스가 포함된 패키지
    |                   |     └── common          # JSON 공통 응답 관련 패키지
    │                   └── security              # 스프링 시큐리티 관련 패키지
    └── resources/
        ├── db/                                   # 데이터베이스 스키마, 데이터 파일 폴더
        ├── mapper/                               # mybatis 매퍼 xml 파일 폴더
        ├── static/                               # 정적 웹 리소스 폴더
        ├── templates/                            # thymeleaf 뷰 템플릿 파일 폴더
        └── application.properties                # 스프링 부트 설정 파일

```

## REST API

### 1. 공통내용
#### 요청 구분
- 인증이 필요없는 요청
  - 회원가입 : `POST /api/auth/signup`
  - 로그인 : `POST /api/auth/login`
  - 토큰 재발급 : `GET /api/auth/refresh`
  - 인증이 필요없는 요청은 요청헤더 정보에 AccessToken 정보가 필요없다.
- 인증이 필요한 요청
  - 게시글 조회 : `GET /api/posts`, `GET /api/posts/게시글번호`
  - 게시글 등록 : `POST /api/posts`
  - 게시글 수정 : `PUT /api/posts/게시글번호`
  - 게시글 삭제 : `DELETE /api/posts/게시글번호`
  - 댓글 조회 : `GET /api/posts/게시글번호/comments`
  - 댓글 등록 : `POST /api/posts/게시글번호/comments`
  - 댓글 삭제 : `DELETE /api/posts/게시글번호/comments/댓글번호`
  - 게시글, 댓글 관련 모든 요청은 인증 후에만 이용할 수 있는 요청이다.
  - 인증이 필요한 요청은 요청헤더 정보에 AccessToken 정보가 반드시 필요하다.
 
#### 응답 정보 구조
- 응답 정보는 아래와 같은 구조를 가진다.
  ```json
  {
    "statusCode": 응답코드
    "message": 응답메세지
    "data" : 응답정데이터
  }
  ```
  - 응답코드
    - HTTP 응답코드 정보다.
    - 성공일 경우 : `200`, `201` 등의 값이다.
    - 클라이언트 오류 인 경우 : `401`

###  2. 회원가입
- 내용 :
  - 신규 사용자를 등록시키는 서비스다.
- 요청
  - 요청 URI : `/api/auth/signup`
  - 요청 방식 : `POST`
  - 요청 데이터 :
    ```json
    {
      "username":"user1",
      "password":"1234",
      "email":"uer1@gmail.com",
      "nickname":"사용자1",
      "tel":"010-1111-1111"
    }
    ```
- 응답
  - 응답 데이터 :
    ```json
    {
      "statusCode": 201,
      "message": "회원가입이 완료되었습니다.",
      "data": null
    }
    ```

### 3. 로그인
- 내용
  - 로그인 정보(아이디, 비밀번호)를 전달해서 사용자 인증을 요청하고, 최종적으로 accessToken, refreshToken 정보를 응답으로 받는다.
- 요청
  - 요청 URI : `/api/auth/login`
  - 요청 방식 : `POST`
  - 요청 데이터 :
    ```json
    {
      "username":"user1",
      "password":"1234"
    }
    ```
- 응답
  - 응답데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "로그인이 완료되었습니다.",
      "data": {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE3NTMzMzYwNzIsImV4cCI6MTc1MzMzNjk3Mn0.jozkeLCaLWAnFmB5On3oi8YWAD0EIbFEV5CSPfC5Tl7mvWRmna3U2mvDHtqpQAGemR14RzE9rnyjkEqj-Y07jA",
        "refreshToken": "e6f00ad8-0162-4ea2-bbb3-39f4329167a7",
        "expiresIn": 900000
      }
    }
    ```

### 4. 토큰 재발급
- 내용
  - accessToken이 만료되면, refreshToken를 제출해서 accessToken을 다시 발급받는다.
  - refreshToken이 만료되면 accessToken은 재발급되지 않는다.
- 요청
  - 요청 URL : `/api/auth/refresh`
  - 요청 방식 : `GET`
  - 요청 파라미터 : `?token=e6f00ad8-0162-4ea2-bbb3-39f4329167a7`
- 응답
  - 응답 데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "토큰이 재발급되었습니다.",
      "data": {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE3NTMzMzY1NzEsImV4cCI6MTc1MzMzNzQ3MX0.T64thOrMiOApfvBpaddAHqd_OU29Q-EBeUdE1ZTBtqe_epTokevDiQHLttLXe0LEfPHRzih0Xyhz82T7xBFzVQ",
        "expiresIn": 900000
      }
    }
    ```

### 5. 게시글 목록 조회
- 내용
  - 게시글 목록 조회 요청을 보내면, 게시글 목록과 페이징정보를 응답으로 받는다.
  - 요청 파라미터로 `page`번호를 추가해서 요청할 수 있다.
- 요청
  - 요청 URL : `/api/posts`
  - 요청 방식 : `GET`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
  - 요청 파라미터 : `?page=1`
- 응답
  - 응답 데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "요청이 성공적으로 처리되었습니다.",
      "data": {
        "items": [
            {
                "postNo": 3,
                "title": "연습3",
                "commentCnt": 0,
                "createdDate": "2025-07-24T14:47:33.393414",
                "name": "홍길동"
            },
            {
                "postNo": 2,
                "title": "연습2",
                "commentCnt": 0,
                "createdDate": "2025-07-24T14:47:33.393414",
                "name": "김유신"
            },
            {
                "postNo": 1,
                "title": "연습1",
                "commentCnt": 0,
                "createdDate": "2025-07-24T14:47:33.393414",
                "name": "홍길동"
            }
        ],
        "paging": {
            "page": 1,
            "totalRows": 3,
            "totalPage": 1,
            "beginPage": 1,
            "endPage": 1,
            "prevPage": 0,
            "nextPage": 2,
            "first": true,
            "last": true
        }
      }
    }
    ```

### 6. 게시글 상세 정보 조회
- 내용
  - 게시글 번호에 해당하는 게시글 상세정보를 응답으로 보낸다.
- 요청
  - 요청 URI : `/api/posts/게시글번호`
  - 요청 방식 : `GET`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
- 응답
  - 응답 데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "요청이 성공적으로 처리되었습니다.",
      "data": {
        "postNo": 1,
        "name": "홍길동",
        "title": "연습1",
        "content": "게시글 연습용 내용입니다.",
        "viewCnt": 0,
        "commentCnt": 0,
        "createdDate": "2025-07-24T14:47:33.393414",
        "updatedDate": "2025-07-24T14:47:33.393414"
      }
    }
    ```

### 7. 게시글 등록
- 내용
  - 게시글 제목, 내용을 서버로 전달해서 게시글 등록 요청을 보낸다.
- 요청
  - 요청 URI : `/api/posts`
  - 요청 방식 : 'POST'
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
  - 요청 데이터 :
    ```json
    {
      "title":"게시글 제목",
      "content":"게시글 내용입니다." 
    }
    ```
- 응답
  - 응답 데이터 : 
    ```json
    {
      "statusCode": 201,
      "message": "게시글이 등록되었습니다.",
      "data": null
    }
    ```

### 8. 게시글 수정
- 내용
  - 변경된 제목, 내용정보를 서버로 전달해서 게시글 수정 요청을 보낸다.
- 요청
  - 요청 URI : `/api/posts/게시글번호`
  - 요청 방식 : `PUT`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
  - 요청 데이터 :
    ```json
    {
      "title":"수정된 게시글 제목",
      "content":"수정된 게시글 내용입니다."
    }
    ```
- 응답
  - 응답데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "게시글이 수정 되었습니다.",
      "data": null
    }
    ```

### 9. 게시글 삭제
- 내용
  - 게시글 번호를 서버로 전달해서 삭제 요청을 보낸다.
- 요청
  - 요청 URI : `/api/posts/게시글번호`
  - 요청 방식 : `DELETE`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
- 응답
  - 응답 데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "게시글이 삭제처리 되었습니다.",
      "data": null
    }
    ```

### 10. 댓글 목록 조회
- 내용
  - 게시글번호를 서버로 보내서 해당 게시글의 댓글 목록을 요청한다.
- 요청
  - 요청 URI : `/api/posts/게시글번호/comments`
  - 요청 방식 : `GET`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
- 응답
  - 응답데이터
    ```json
    {
      "statusCode": 200,
      "message": "요청이 성공적으로 처리되었습니다.",
      "data": [
        {
            "commentNo": 1,
            "content": "첫번째 댓글입니다.",
            "createdDate": "2025-07-24T16:01:02.181428",
            "name": "홍길동"
        },
        {
            "commentNo": 2,
            "content": "두번째 댓글입니다.",
            "createdDate": "2025-07-24T16:01:02.181428",
            "name": "김유신"
        },
        {
            "commentNo": 3,
            "content": "세번째 댓글입니다.",
            "createdDate": "2025-07-24T16:01:02.181428",
            "name": "홍길동"
        }
      ]
    }
    ```

### 11. 댓글 추가
- 내용
  - 댓글내용을 서버로 보내서 댓글 등록을 요청한다.
- 요청
  - 요청 URI : `/api/posts/게시글번호/comments`
  - 요청 방식 : `POST`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
  - 요청 데이터 :
    ```json
    {
      "content":"댓글내용입니다."
    }
    ```
- 응답
  - 응답 데이터
    ```json
    {
      "statusCode": 201,
      "message": "댓글이 등록되었습니다.",
      "data": null
    }
    ```

### 12. 댓글 삭제
- 내용
  - 댓글 번호를 서버로 보내서 댓글 삭제를 요청한다.
- 요청
  - 요청 URI : `/api/posts/게시글번호/comments/댓글번호`
  - 요청 방식 : `DELETE`
  - 요청 헤더 정보 : `Authorization: Bearer <AccessToken>`
- 응답
  - 응답데이터 :
    ```json
    {
      "statusCode": 200,
      "message": "댓글 삭제되었습니다.",
      "data": null
    }
    ```

  
