import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchPostDetail } from '../../api/postApi';
import PageContainer from '../../layouts/PageContainer';
import PostDetail from '../../components/posts/PostDetail';

const DetailPage = () => {
    const { no } = useParams();
    const [post, setPost] = useState({});

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

    if (!post) {
        return <div>로딩중</div>
    }
    
    return (
        <PageContainer title="게시글 상세">
            <PostDetail post={post} />
        </PageContainer>
    );
}


export default DetailPage;