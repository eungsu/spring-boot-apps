import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import MainPage from './pages/MainPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import PostListPage from './pages/posts/ListPage';
import PostDetailPage from './pages/posts/DetailPage';

function ProtectedRoute({ children }) {
  const { auth } = useAuth();
  return auth.isAuthenticated ? children : <Navigate to={'/login'} />;
}

const App = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/posts/list" element={
              <ProtectedRoute>
                <PostListPage />
              </ProtectedRoute>
            } />
            <Route path="/posts/detail/:no" element={
              <ProtectedRoute>
                <PostDetailPage />
              </ProtectedRoute>
            } />
          </Routes>        
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App;
