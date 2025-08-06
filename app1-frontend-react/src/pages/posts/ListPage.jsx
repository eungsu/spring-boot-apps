import { useEffect, useState } from 'react';
import  { fetchPosts } from '../../api/posts';
import MainContentLayout from '../../layouts/MainContentLayout';
import PostList from '../../components/posts/PostList';
import Pagination from '../../components/Pagination';

const ListPage = () => {
    const [posts, setPosts] = useState([]);
    const [paging, setPaging] = useState({});
    const [page, setPage] = useState(1);

    const getPosts = async (pageNo) => {
        try {
            const response = await fetchPosts(pageNo);
            setPosts(response.data.items);
            setPaging(response.data.paging);
        } catch (error) {
            console.error('게시글 조회 실패', error.response.data.message);
        }
    }
    useEffect(() => {
        getPosts(page);
    }, [page]);

    return (
        <MainContentLayout title="게시글 목록">
            <PostList posts={posts} />
            <Pagination paging={paging} onPageChange={setPage}/>
        </MainContentLayout>
    )
};

export default ListPage;
