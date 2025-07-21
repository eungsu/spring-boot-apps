import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainPage from './pages/MainPage';
import PostListPage from './pages/posts/ListPage';
import PostDetailPage from './pages/posts/DetailPage';

const App = () => {
  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/posts/list" element={<PostListPage />} />
          <Route path="/posts/detail/:no" element={<PostDetailPage />} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;
