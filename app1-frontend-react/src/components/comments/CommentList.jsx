const CommentList = ({ comments, onDelete }) => {
    return (
        <div className="mb-3">
            <h4> 댓글 (<span>{comments.length}</span>)</h4>
            {comments.length === 0 ? (
                <div className="alert alert-info small mb-3">
                    등록된 댓글이 없습니다.
                </div>
            ) : (
                <div className="small mb-3">
                    {comments.map(comment => (
                        <div className="mb-3 border p-3" key={comment.commentNo}>
                            <div className="mb-1">
                                <strong><i className="bi bi-person-cicle"></i> <span>{comment.name}</span></strong>
                                <span> | <i className="bi bi-calendar-check"></i> {new Date(comment.createdDate).toLocaleString()}</span>
                            </div>
                            <div className="card-text">
                                <p>{comment.content}</p>
                            </div>
                            <div className="text-end">
                                <button type="button" className="btn btn-sm btn-outline-danger"
                                    onClick={() => onDelete(comment.commentNo)}>삭제</button>
                            </div>
                        </div>
                    ))}
                </div>                
            )}
        </div>
    );
};

export default CommentList;