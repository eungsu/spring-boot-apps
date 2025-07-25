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

### 컴퓨터에 도커가 설치되어 있지 않은 경우
1. 원격 저장소에서 소스코드를 내려받는다.
   ```bash
   git clone https://github.com/eungsu/spring-boot-apps.git
   ```
2. `app1-backend` 애플리케이션을 실행한다.
   ```bash
   cd spring-boot-apps
   cd app1-backend
   mvnw spring-boot:run 
   ```
3. `app1-frontend-react` 애플리케이션을 실행한다.
   ```bash
   cd spring-boot-apps
   cd appl-frontend-react
   npm install
   npm run dev
   ```
4. 실행이 완료되면 웹 브라우저를 켜고, `http://localhost:5173`으로 접속한다.

