
const PostDetail = ({ post }) => {
    return (
        <div className="mb-3">
            <h1>{post.title}</h1>
            <div>
                <span className="me-3"><i className="bi bi-person-fill"></i> 작성자: <strong className="text-dark">{post.name}</strong></span>
                <span className="me-3"><i className="bi bi-calendar-check-fill"></i> 작성일: {new Date(post.createdDate).toLocaleString()}</span>
                <span className="me-3"><i className="bi bi-eye-fill"></i> 조회수: {post.viewCnt}</span>
                <span className="me-3"><i className="bi bi-list"></i> 댓글수: {post.commentCnt}</span>
            </div>
        </div>
    );
};

export default PostDetail;
