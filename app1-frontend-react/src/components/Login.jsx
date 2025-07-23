import { useState } from 'react';
import { Link, useNavigate  } from 'react-router-dom';
import { login } from '../api/auth';
import { useAuth } from '../context/AuthContext';

const Login = () => {
    const { saveTokens } = useAuth();
    const [loginData, setLoginData] = useState({
        username: 'hong',
        password: '1234'
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLoginData({ ...loginData, [name]: value});
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await login(loginData);
            const { accessToken, refreshToken, expiresIn } = response.data;
            saveTokens({ accessToken, refreshToken, expiresIn });

            alert(response.message);
            navigate('/');
        } catch (error) {
            alert("로그인 오류 발생: " + error.response.data.message);
        }
    };

    return (
        <>
            <form onSubmit={handleLogin}>
                <div className="mb-3">
                    <label className="form-label">아이디</label>
                    <input type="text" className="form-control" name="username" value={loginData.username} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label className="form-label">비밀번호</label>
                    <input type="password" className="form-control" name="password" value={loginData.password} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button type="submit" className="btn btn-primary w-100 mb-3">로그인</button>
                    <Link to={'/signup'} type="button" className="btn btn-outline-secondary w-100">취소</Link>
                </div>
            </form>
        </>
    );
};

export default Login;