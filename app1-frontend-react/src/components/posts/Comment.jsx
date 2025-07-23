import { useState } from 'react';

const CommentList = ({ comments, onAdd, onDelete }) => {
    const [content, setContent] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        onAdd(content);
        setContent('');
    }

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
                                <span> | <i className="bi bi-calendar-check"></i> {comment.createdDate}</span>
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
            <div className="mb-3">
                <h5>댓글 작성</h5>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <textarea className="form-control" rows="3"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                            placeholder="댓글을 입력하세요"></textarea>
                    </div>
                    <div className="text-end">
                        <button type="submit" className="btn btn-sm btn-primary">댓글 작성</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default CommentList;