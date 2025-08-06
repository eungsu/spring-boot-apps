import ContentLayout from '../layouts/ContentLayout';
import Login from '../components/Login';

const LoginPage = () => {
    return (
        <ContentLayout title="로그인">
            <Login />
        </ContentLayout>
    );
};

export default LoginPage;