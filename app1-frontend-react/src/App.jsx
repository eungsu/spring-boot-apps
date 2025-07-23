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