import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import  { createPost } from '../../api/postApi';

const PostForm  = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: '',
        content: ''
    });

    const handleChange = (e) => {
        const { name, value} = e.target;
        setFormData({...formData, [name]:value});
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await createPost(formData);
            alert(response.message);
            navigate('/posts/list');
        } catch (error) {
            alert('게시글 등록 실패: ' + error.response.data.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-3">
                <label className="form-label">제목</label>
                <input type="text" className="form-control" name="title" onChange={handleChange} />
            </div>
            <div className="mb-3">
                <label className="form-label">내용</label>
                <textarea className="form-control" rows="7" name="content" onChange={handleChange} ></textarea>
            </div>
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <button type="submit" className="btn btn-primary">
					<i className="bi bi-send-fill me-2"></i> 등록
				</button>
				<Link to="/posts/list" className="btn btn-secondary">
					<i className="bi bi-x-circle-fill me-2"></i> 취소
				</Link>
            </div>
        </form>
    );

};

export default PostForm;