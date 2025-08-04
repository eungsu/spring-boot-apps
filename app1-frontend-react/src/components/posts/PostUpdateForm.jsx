import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { updatePost } from '../../api/posts';

const PostUpdateForm = ({ post }) => {
    const navigate = useNavigate();
    const [title, setTitle] = useState(post.title);
    const [content, setContent] = useState(post.content);

    const handleUpdate = async (e) => {
        e.preventDefault();
        try {
            await updatePost(post.postNo, { title, content });
            alert("게시글이 수정되었습니다.");
            navigate(`/posts/detail/${post.postNo}`)
        } catch (error) {
            alert('게시글 수정 실패: ' + error.response.data.message);
        }
    }

    return (
        <form onSubmit={handleUpdate}>
            <div className="mb-3">
                <label className="form-label">제목</label>
                <input type="text" className="form-control" value={title} onChange={(e) => setTitle(e.target.value)} />
            </div>
            <div className="mb-3">
                <label className="form-label">내용</label>
                <textarea className="form-control" rows="7" value={content}  onChange={(e) => setContent(e.target.value)}></textarea>
            </div>
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <button type="submit" className="btn btn-primary">
					<i className="bi bi-send-fill me-2"></i> 수정
				</button>
				<Link to="/posts/list" className="btn btn-secondary">
					<i className="bi bi-x-circle-fill me-2"></i> 취소
				</Link>
            </div>
        </form>
    );
};

export default PostUpdateForm;
