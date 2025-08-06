import MainContent from '../../layouts/MainContent';
import PostForm from '../../components/posts/PostForm';

const FormPage = () => {
    return (
        <MainContent title="신규 게시글 등록">
            <PostForm />
        </MainContent>
    );
};

export default FormPage;