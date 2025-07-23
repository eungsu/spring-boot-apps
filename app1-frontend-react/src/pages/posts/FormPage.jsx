import PageContainer from '../../layouts/PageContainer';
import PostForm from '../../components/posts/PostForm';

const FormPage = () => {
    return (
        <PageContainer title="신규 게시글 등록">
            <PostForm />
        </PageContainer>
    );
};

export default FormPage;