import ContentLayout from '../layouts/ContentLayout';
import Signup from '../components/Signup';

const SignupPage = () => {
    return (
        <ContentLayout title="회원가입">
            <Signup />
        </ContentLayout>
    );
}

export default SignupPage;