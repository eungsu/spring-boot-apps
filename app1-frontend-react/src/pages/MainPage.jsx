import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import BaseLayout from '../layouts/BaseLayout';

const MainPage = () => {
    const { auth} = useAuth();

    return (
        <BaseLayout>
            <div className="container mt-4">
                <div className="row mb-3">
                    <div className="col-12">
                        <div className="p-5 mb-4 bg-light rounded-3">
                            <h1 className="display-4">샘플 애플리케이션</h1>
                            <p className="lead">Spring Boot, Spring Security, mybatis, H2, react를 활용한 게시판 웹 애플리케이션입니다.</p>
                            <hr/>
                            {!auth.isAuthenticated ? (
                                <>
                                    <Link to={"/signup"} className="btn btn-primary btn-lg mt-3">회원가입</Link>
                                    <Link to={"/login"} className="btn btn-outline-primary btn-lg  mt-3 ms-2">로그인</Link>
                                </>
                            ) : (
                                <>
                                    <Link to={"/posts/list"} className="btn btn-primary btn-lg mt-3">게시글 목록</Link>
                                </>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </BaseLayout>
    )
};

export default MainPage;