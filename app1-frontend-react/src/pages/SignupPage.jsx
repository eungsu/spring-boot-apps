import MainContentLayout from '../layouts/MainContentLayout';
import Signup from '../components/Signup';

const SignupPage = () => {
    return (
        <MainContentLayout title="회원가입">
            <Signup />
        </MainContentLayout>
    );
}

export default SignupPage;