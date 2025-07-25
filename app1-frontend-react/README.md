#  프론트엔드 애플리케이션
회원가입, 로그인, 게시글, 댓글 관리 기능을 지원하는 react기반의 프론트 엔드 애플리케이션이다.

## 프론트 애플리케이션 실행하기
### 컴퓨터에 도커가 설치되어 있는 경우

1. 원격 저장소에서 소스코드를 내려받는다.
   ```bash
   git clone https://github.com/eungsu/spring-boot-apps.git
   ```
2. spring-boot-apps 폴더로 이동한다.
   ```bash
   cd spring-boot-apps
   ```
3. 도커 컴포즈 명령어로 백엔드 애플리케이션(app1-backed)과 프론트엔드 애플리케이션(app1-frontend-react)을 실행시킨다.
   ```bash
   docker-compose -f docker-compose/react.yml up --build -d

   # -f docker-compose/react.yml : 도커 컴포즈 설정파일을 지정한다.
   # --build                     : Dockerfile로 도커이미지를 빌드해서 컨테이너 실행에 사용한다.
   # -d                          : 백그라운드로 실행시킨다. 
   ```
   - 도커 컴포즈 설정파일(`react.yml`)에는
     백엔드 애플리케이션(`app1-backend`: spring boot 애플리케이션)과
     프론트엔드 애플리케이션(`app1-frontend-react`: react 애플리케이션) 실행정보가 포함되어 있다.

4. 실행이 완료되면 웹 브라우저를 켜고, `http://localhost:3000`으로 접속한다.
5. 아래 명령어를 실행하면 종료된다.
   ```bash
   docker-compose -f docker-compose/react.yml down
   ```

### 컴퓨터에 도커가 설치되어 있지 않은 경우
1. node.js를 설치한다.
   - https://nodejs.org/en/download 사이트에 접속한다.
   - 내컴퓨터의 운영체제에 맞는 node.js를 다운로드 받는다.
   - 다운로드 받은 설치파일을 실행해서 node.js를 설치한다.
2. 원격 저장소에서 소스코드를 내려받는다.
   ```bash
   git clone https://github.com/eungsu/spring-boot-apps.git
   ```
3. `app1-backend` 애플리케이션을 실행한다.
   ```bash
   cd spring-boot-apps
   cd app1-backend
   mvnw spring-boot:run 
   ```
4. `app1-frontend-react` 애플리케이션을 실행한다.
   ```bash
   cd spring-boot-apps
   cd appl-frontend-react
   npm install
   npm run dev
   ```
5. 실행이 완료되면 웹 브라우저를 켜고, `http://localhost:5173`으로 접속한다.
6. 각각의 Cmd 창에서 `Ctrl+C`를 눌러서 애플리케이션을 종료시킨다.

## 애플리케이션 구조

### 디렉토리 구조조
```pqsql
├── src/
│   ├── api/
│   │   ├── axiosInstance.js                   # axios 인스턴스를 제공한다. interceptor를 이용해서 매 요청시 jwt 토큰을 요청에 포함시킨다.
│   │   ├── auth.js                            # 회원가입, 로그인, 토큰 재발행 관련 API 함수가 정의되어 있다.
│   │   └── postApi.js                         # 게시글, 댓글 관련 CRUD API 함수가 정의되어 있다.
│   ├── components/
│   │   ├── posts/
│   │   │   ├── Comment.jsx                    # 댓글목록, 댓글폼을 표시하는 컴포넌트다.
│   │   │   ├── PostDetail.jsx                 # 게시글 상세정보를 표시하는 컴포넌트다.
│   │   │   ├── PostForm.jsx                   # 게시글 등록폼을 표시하는 컴포넌트다.
│   │   │   ├── PostList.jsx                   # 게시글 목록을 표시하는 컴포넌트다.
│   │   │   └── PostUpdateForm.jsx             # 게시글 수정폼을 표시하는 컴포넌트다.
│   │   ├── Navbar.jsx                         # 내비바를 표시하는 컴포넌트다
│   │   ├── Pagination.jsx                     # 페이지네이션을 표시하는 컴포넌트다.
│   │   ├── Login.jsx                          # 로그인폼을 표시하는 컴포넌트다.
│   │   └── Signup.jsx                         # 회원가입폼을 표시하는 컴포넌트다.
│   ├── context/
│   │   └── AuthContext.jsx                    # JWT 토큰과 인증상태 정보를 관리하는 React Contenxt다.
│   ├── layouts/
│   │   ├── BaseLayout.jsx                     # 모든 페이지에 공통으로 적용되는 레이아웃이 정의되어 있다.(`Navbar.jsx`를 포함한다.)
│   │   └── PageContainer.jsx                  # 메인페이지를 제외한 대부분의 페이지에 적용되는 스타일용 컴포넌트다.
│   ├── pages/
│   │   ├── posts/
│   │   │   ├── DetailPage.jsx                 # 게시글 상세 조회 페이지다. `PostDetail.jsx`와 `Comment.jsx`를 포함한다.
│   │   │   ├── FormPage.jsx                   # 게시글 작성 페이지다. `PostForm.jsx`를 포함한다.
│   │   │   ├── ListPage.jsx                   # 게시글 목록 페이지다. `PostList.jsx`와 `Pagination.jsx`를 포함한다.
│   │   │   └── UpdateFormPage.jsx             # 게시글 수정 페이지다. `PostUpdateForm.jsx`를 포함한다.
│   │   ├── MainPage.jsx                       # 메인 페이지다.
│   │   ├── LoginPage.jsx                      # 로그인 페이지다. `Login.jsx`를 포함한다.
│   │   └── SignupPage.jsx                     # 회원가입 페이지다. `Signup.jsx`를 포함한다.
│   ├── App.jsx                                # 전체 라우팅 및 레이아웃 구성을 위한 최상위 루트 컴포넌트다.
│   ├── main.jsx                               # 리액트 앱의 진입점이 되는 파일이다. 
├── .gitignore
├── Dockerfile                                 # 도커 이미지 빌드 파일이다.
├── eslint.config.js                           # ESLint 설정 파일이다. 코드스타일과 문법 오류 검사를 위한 규칙을 정의한다.
├── vite.config.js                             # Vite 번들러의 구성 파일이다.
├── package.json                               # 프로젝트의 메타데이터(이름, 버전, 의존성 등)와 스크립트가 정의된 파일이다.
├── package-lock.json                          # npm install 시 설치된 의존성의 정확한 버전 정보와 의존성 트리를 저장하는 파일이다.
└── index.html                                 # React 애플리케이션이 주입되는 기본 HTML 파일이다.
```

### 애플리케이션의 주요 구성요소
#### 레이아웃 (Layout)
> 여러 페이지에서 공통으로 사용되는 UI 구조를 정의한다.
> (예: 내비게이션바, 페이지 컨테이너 등)

| 구성요소 | 설명 |
|---|---|
| `BaseLayout` | 전체 앱의 기본 골격을 제공한다. |
| `Navbar` | 상단 내비게이션 바 (메뉴, 로고, 로그인/로그아웃 등 포함) |
| `PageContainer` | 각 페이지의 내용을 담는 공통 영력을 정의한다. 실제 페이지가 이 영역 안에서 렌더링된다. |

#### 페이지 (Page)
> 라우터(`react-router-dom`)와 연결되어 URL 경로에 따라 렌더링되는 단위다.  
> **페이지는 앱의 경로를 기반으로 한 큰 화면 단위**다.  
> 주로 **페이지 단위의 로직, 데이터 매칭, 상태 관리** 등을 담당한다.  
> 내부적으로 여러 컴포넌트들을 조합해 하나의 화면을 구성한다.  
> 예: 'MainPage', `LoginPage`, `SignupPage`, `posts/ListPage`, `posts/DetailPage`, `posts/FormPage`, `posts/UpdateFormPage` 등  
> 이들 페이지는 `<Route path="..." />`에 연결되어 URL 경로와 매칭된다.

#### 컴포넌트 (Component)
> 특정 기능이나 UI 요소를 담당하는 재사용 가능한 단위다.  
> 보통 하나의 목적을 가진 기능단위이며, 작게 나눌 수록 더 좋다.  
> **컴포넌트는 페이지 안에서 UI 및 기능을 담당하는 작고 재사용 가능한 단위**입니다.  
> 예: `compontents/Login`, `components/Signup`, `components/Pagination`, `components/posts/PostList`, `components/posts/PostDetail`, `components/posts/PostForm`, `components/posts/PostUpdateForm`,    

### 컴포넌트 포함관계

#### 메인 페이지
```pqsql
App
 └── BaseLayout       # 페이지의 기본 레이아웃을 정의하는 컴포넌트다.
      ├── Navbar      # 내비바를 표시하는 컴포넌트다.
      └── MainPage    # 메인 페이지를 표시한다.
```

#### 회원가입 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer       # 페이지 제목과 내용을 카드형식으로 표현하는 컴포넌트다.
           └── SignupPage     # 회원가입 페이지를 표시한다.
                └── Signup    # 회원가입 폼을 표시하는 컴포넌트다.
```

#### 로그인 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer
           └── LoginPage     # 로그인 페이지를 표시한다.
                └── Login    # 로그인 폼을 표시하는 컴포넌트다.
```

#### 게시글 목록 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer
           └── ListPage           # 게시글 목록 페이지를 표시한다.
                ├── PostList      # 게시글 목록을 표시하는 컴포넌트다.
                └── Pagination    # 페이지네이션을 표시하는 컴포넌트다.
```

#### 게시글 상세 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer
           └── DetailPage         # 게시글 상세 페이지를 표시한다.
                ├── PostDetail    # 게시글 상세정보를 표시하는 컴포넌트다.
                └── Comment       # 댓글 목록과 댓글입력폼을 표시하는 컴포넌트다.
```

#### 게시글 등록 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer
           └── FormPage         # 게시글 등록 페이지를 표시한다.
                └── PostForm    # 게시글 등록폼을 표시하는 컴포넌트다. 
```

#### 게시글 수 페이지
```pqsql
App
 └── BaseLayout
      ├── Navbar
      └── PageContainer
           └── UpdateFormPage         # 게시글 수정 페이지를 표시한다.
                └── PostUpdateForm    # 게시글 수정폼을 표시하는 컴포넌트다.
```


