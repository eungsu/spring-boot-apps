/**
  App.jsx
  - 리액트 애플리케이션의 루트 컴포넌트다.
  - 앱 전체의 라우팅 구조, 인증 컨텍스트(AuthContext), 접근 제어(Protected Route)를 정의한다.
*/

/*
  React Router 관련 import
  - BrowserRouter : HTML5 History API를 사용하여 URL을 관리하는 라우터
  - Routes : 여러 개의 Route를 감싸는 컴포넌트다.
  - Route : 경로(path)와 해당 경로에 매핑할 컴포넌트(element)를 설정한다.
  - Navigate : 리다이렉션을 수행하는 컴포넌트다.
  - Outlet : 중첩 라우트에서 경로에 해당하는 자식 컴포넌트를 렌더링할 위치를 지정하는 플레이스홀더 컴포넌트다.
*/
import { BrowserRouter, Routes, Route, Navigate, Outlet } from 'react-router-dom';

/*
  인증 관련 import
  - AuthProvider : Context API를 통해 애플리케이션 전역에 인증 산태를 제공하는 Provider 컴포넌트다. 모든 하위 컴포넌트에서 접근가능하다.
  - useAuth : AuthContext에서 현재 로그인 상태나 인증관련 메소드를 가져오는 사용자 정의 Hook 이다.
*/
import { AuthProvider, useAuth } from './context/AuthContext';

/*
  페이지 컴포넌트 import
  - 각 URL 경로에 매핑되는 페이지 컴포넌드들이다.
*/
import MainPage from './pages/MainPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import PostListPage from './pages/posts/ListPage';
import PostFormPage from './pages/posts/FormPage';
import PostDetailPage from './pages/posts/DetailPage';
import PostUpdateFormPage from './pages/posts/UpdateFormPage';

/*
  ProtectedRoute 선언
  - ProtectedRoute는 React Router에서 인증기반 접근 제어를 구현하기 위한 컴포넌트다.
  - 사용자가 로그인 상태일 경우에만 현재 경로에 맞는 자식 라우트 컴포넌트 출력한다.
  - 로그인하지 않은 사용자는 자동으로 /login 페이지로 이동한다.
  - 동작 순서
    1. 사용자가 /posts/create로 이동
    2. 부모 <Route element={<ProtectedRoute />}>가 먼저 렌더링됨
    3. ProtectedRoute에서 useAuth()로 로그인 여부 확인
    4. 로그인한 상태면 <Outlet /> 위치에 자식인 <PostFormPage /> 렌더링
    5. 로그인하지 않은 상태면 /login 페이지로 이동
*/
function ProtectedRoute({  }) {
  // useAuth()는 AuthContext에서 현재 사용자의 인증상태 정보를 가져온다.
  const { auth } = useAuth();
  return auth.isAuthenticated ? <Outlet /> : <Navigate to={'/login'} />;
}

/*
  리액트 + 리액트 라우터를 기반으로 하는 리액트 애플리케이션의 루트 컴포넌트다.
  - <AuthProvider>
    - Context API 기반 인증 상태 전역 관리 컴포넌트다.
    - 앱 전체에서 로그인 상태, JWT 토큰관리 함수 등을 제공한다.
    - 인증 상태를 사용하는 모든 컴포넌트는 <AuthProvider> 하위에서 렌더링되어야 한다.
  - <BrowserRouter>
    - HTML5의 history API를 사용하는 SPA(Single Page Application) Router다.
    - 브라우저 주소창의 URL을 동기화하고, URL과 매핑되는 컴포넌트를 렌더링한다.
  - <Routes>
    - 여러 개의 <Route>를 그룹화한다.
    - <Route>는 반드시 <Routes>안에 작성한다.
  - <Route>
    - URL 경로와 컴포넌트를 매핑한다.
*/
const App = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route element={<ProtectedRoute />}>
                <Route path="/posts/create" element={<PostFormPage />} />
                <Route path="/posts/list" element={<PostListPage />} />
                <Route path="/posts/detail/:no" element={<PostDetailPage />} />
                <Route path="/posts/update/:no" element={<PostUpdateFormPage />} />
            </Route>
          </Routes>        
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App;
