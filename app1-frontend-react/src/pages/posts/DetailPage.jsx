import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { fetchPostDetail, deletePost } from '../../api/postApi';
import PageContainer from '../../layouts/PageContainer';
import PostDetail from '../../components/posts/PostDetail';

const DetailPage = () => {
    const { no } = useParams();
    const [post, setPost] = useState({});
    const navigate = useNavigate();

    const getPost = async (postNo) => {
        try {
            const response = await fetchPostDetail(postNo);
            setPost(response.data);
        } catch (error) {
            console.error('게시글 상세 조회 실패', error.response.data.message);
        }
    }

    useEffect(() => {
        getPost(no);
    }, [no]);

    const handleDelete = async (postNo) => {
        try {
            await deletePost(postNo);
            navigate('/posts/list');
        } catch (error) {
            alert('게시글 삭제 실패 : ' + error.response.data.message);
        }
    }

    
    return (
        <PageContainer title="게시글 상세">
            <PostDetail post={post} onDelete={handleDelete}/>
        </PageContainer>
    );
}


export default DetailPage;