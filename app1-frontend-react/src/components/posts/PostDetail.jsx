import { Link } from 'react-router-dom';

const PostDetail = ({ post, onDelete }) => {

    const handleDeletePost = () => {
        if (window.confirm("게시글을 삭제하시겠습니까?")) {
            onDelete(post.postNo);
        }
    };

    return (
        <>
            <div className="mb-3">
                <h1>{post.title}</h1>
                <div>
                    <span className="me-3"><i className="bi bi-person-fill"></i> 작성자: <strong className="text-dark">{post.name}</strong></span>
                    <span className="me-3"><i className="bi bi-calendar-check-fill"></i> 작성일: {new Date(post.createdDate).toLocaleString()}</span>
                    <span className="me-3"><i className="bi bi-eye-fill"></i> 조회수: {post.viewCnt}</span>
                    <span className="me-3"><i className="bi bi-list"></i> 댓글수: {post.commentCnt}</span>
                </div>
            </div>
            <hr/>
            <div className="card-text mb-3">
                <p>{post.content}</p>
            </div>
            <div className="text-end mb-3">
                <Link to={`/posts/update/${post.postNo}`} className="btn btn-warning me-2">
                    <i className="bi bi-pencil-square"></i> 수정
                </Link>
                <button onClick={handleDeletePost} className="btn btn-danger me-2">
                    <i className="bi bi-trash-fill"></i> 삭제
                </button>
                <Link to={`/posts/list`} className="btn btn-secondary me-3">
                    <i className="bi bi-list"></i> 목록으로
                </Link>
            </div>
        </>
    );
};

export default PostDetail;
