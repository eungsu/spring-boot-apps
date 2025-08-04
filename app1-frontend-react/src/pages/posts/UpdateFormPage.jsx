import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { fetchPostDetail } from '../../api/posts';
import PageContainer from '../../layouts/PageContainer';
import PostUpdateForm from '../../components/posts/PostUpdateForm';

const UpdateFormPage = () => {
    const { no } = useParams();
    const [post, setPost] = useState(null);

    const getPost = async (postNo) => {
        try {
            const response = await fetchPostDetail(postNo);
            setPost(response.data);
        } catch (error) {
            console.error('게시글 조회 실패', error.response.data.message);
        }
    };

    useEffect(() => {
        getPost(no);
    }, [no]);

    if (!post) {
        return (
            <PageContainer title="게시글 수정">
                <div>게시글을 찾을 수 없습니다.</div>
            </PageContainer>
        )
    }

    return (
        <PageContainer title="게시글 수정">
            {post === null ? (
                <div className="card-text">로딩 중 ...</div>
            ) : (
                <PostUpdateForm post={post}/>
            )}
        </PageContainer>
    );
}

export default UpdateFormPage;
