import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { fetchPostDetail, deletePost, addComment, fetchComments, deleteComment } from '../../api/posts';
import MainContentLayout from '../../layouts/MainContentLayout';
import PostDetail from '../../components/posts/PostDetail';
import Comment from '../../components/posts/Comment';

const DetailPage = () => {
    const { no } = useParams();
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState([]);
    const navigate = useNavigate();

    const getPost = async (postNo) => {
        try {
            const response = await fetchPostDetail(postNo);
            setPost(response.data);
        } catch (error) {
            console.error('게시글 상세 조회 실패', error.response.data.message);
        }
    }
    const getComments = async (postNo) => {
        try {
            const response = await fetchComments(postNo);
            setComments(response.data);
        } catch (error) {
            console.error('게시글 상세 조회 실패', error.response.data.message);
        }
    }

    useEffect(() => {
        getPost(no);
        getComments(no);
    }, [no]);

    const handleDelete = async (postNo) => {
        try {
            await deletePost(postNo);
            navigate('/posts/list');
        } catch (error) {
            alert('게시글 삭제 실패 : ' + error.response.data.message);
        }
    }

    const handleAddComment = async (content) => {
        try {
            await addComment(no, content);
            await getComments(no);
        } catch (error) {
            alert('댓글 등록 실패 : ' + error.response.data.message);
        }
    }

    const handleDeleteComment = async (commentNo) => {
        try {
            await deleteComment(no, commentNo);
            await getComments(no);
        } catch (error) {
            alert('댓글 삭제 실패 : ' + error.response.data.message);
        }
    }

    return (
        <MainContentLayout title="게시글 상세">
            {post === null ? (
                <p className="card-text">로딩 중 ...</p>
            ) : (
                <>
                    <PostDetail post={post} onDelete={handleDelete}/>
                    <Comment comments={comments}  onAdd={handleAddComment} onDelete={handleDeleteComment}/>
                </>
            )}
        </MainContentLayout>
    );
}

export default DetailPage;
