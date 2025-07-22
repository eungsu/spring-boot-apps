import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
    const { auth, clearTokens } = useAuth();

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div className="container-fluid">
                <Link className="navbar-brand" to={"/"}>샘플 애플리케이션</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-support-content"
                    aria-controls="navbar-support-content" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbar-support-content">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link" to={"/"}>홈</Link>
                        </li>
                        {auth.isAuthenticated && (
                            <li className="nav-item">
                                <Link className="nav-link" to={"/posts/list"}>게시글</Link>
                            </li>
                        )}
                    </ul>
                    <ul className="navbar-nav mb-2 mb-lg-0">
                        {!auth.isAuthenticated ? (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to={"/login"}>로그인</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={"/signup"}>회원가입</Link>
                                </li>
                            </>
                        ) : (
                            <li className="nav-item">
                                <button className="nav-link" onClick={clearTokens}>로그아웃</button>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;