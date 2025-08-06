import ContentLayout from '../../layouts/ContentLayout';
import PostForm from '../../components/posts/PostForm';

const FormPage = () => {
    return (
        <ContentLayout title="신규 게시글 등록">
            <PostForm />
        </ContentLayout>
    );
};

export default FormPage;