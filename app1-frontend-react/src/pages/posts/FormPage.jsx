import MainContentLayout from '../../layouts/MainContentLayout';
import PostForm from '../../components/posts/PostForm';

const FormPage = () => {
    return (
        <MainContentLayout title="신규 게시글 등록">
            <PostForm />
        </MainContentLayout>
    );
};

export default FormPage;