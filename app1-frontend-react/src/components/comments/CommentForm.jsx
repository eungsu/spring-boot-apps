import { useState } from 'react';

const CommentForm = ({ onAdd }) => {
    const [content, setContent] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        onAdd(content);
        setContent('');
    }
    
    return (
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
    );
};

export default CommentForm;