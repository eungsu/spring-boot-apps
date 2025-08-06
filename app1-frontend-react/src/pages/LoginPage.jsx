import MainContentLayout from '../layouts/MainContentLayout';
import Login from '../components/Login';

const LoginPage = () => {
    return (
        <MainContentLayout title="로그인">
            <Login />
        </MainContentLayout>
    );
};

export default LoginPage;