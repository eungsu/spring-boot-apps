import CommentList from '../components/comments/CommentList';
import CommentForm from '../components/comments/CommentForm';

const CommentLayout = ({ comments, onAdd, onDelete }) => {
    return (
        <>
            <CommentList comments={comments} onDelete={onDelete} />
            <CommentForm onAdd={onAdd}/>
        </>
    );
}

export default CommentLayout;