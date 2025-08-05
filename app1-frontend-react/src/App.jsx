/**
  App.jsx
  - 리액트 애플리케이션의 루트 컴포넌트다.
  - 앱 전체의 라우팅 구조, 인증 컨텍스트(AuthContext), 접근 제어(Protected Route)를 정의한다.
  - 주요 구성 요소
    - App.jsx : 전체 앱의 루트 컴포넌트다. 라우팅 및 인증처리를 제공한다.
    - BrowserRouter : React Router의 URIL 기반 라우터
    - Routes, Route : URL 경로에 따라 컴포넌트를 렌더링한다.
    - AuthProvider : 로그인 상태를 전역에서 관리하는 컨텍스트 제공자
    - ProtectedRoute : 인증된 사용자만 접근 가능한 라우트를 정의한다.
    - Outlet : 중첩된 라우트의 위치 지정
*/
import { BrowserRouter, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import MainPage from './pages/MainPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import PostListPage from './pages/posts/ListPage';
import PostFormPage from './pages/posts/FormPage';
import PostDetailPage from './pages/posts/DetailPage';
import PostUpdateFormPage from './pages/posts/UpdateFormPage';

function ProtectedRoute({  }) {
  const { auth } = useAuth();
  return auth.isAuthenticated ? <Outlet /> : <Navigate to={'/login'} />;
}

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
