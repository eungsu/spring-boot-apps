import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signup } from '../api/auth';

const Signup = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        username: '',
        password: '',
        email: '',
        nickname: '',
        tel: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await signup(formData);
            alert(response.message);
            navigate('/');
        } catch (error) {
            console.log("error", error);
            alert("회원가입 오류 발생: " + error.response.data.message);
        }
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">아이디</label>
                    <input type="text" className="form-control" name="username" value={formData.username} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">비밀번호</label>
                    <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">이메일</label>
                    <input type="text" className="form-control" name="email" value={formData.email} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">이름</label>
                    <input type="text" className="form-control" name="nickname" value={formData.nickname} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">전화번호</label>
                    <input type="text" className="form-control" name="tel" value={formData.tel} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button type="submit" className="btn btn-primary w-100 mb-3">회원가입</button>
                    <Link to={'/'} className="btn btn-outline-secondary w-100">취소</Link>
                </div>
            </form>
        </>
    );
}

export default Signup;
