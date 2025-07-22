import { Link } from 'react-router-dom';

const PostList = ({ posts }) => {
    return (
        <table className="table">
            <thead>
                <tr>
                    <th scope="col" style={{width: "8%"}}>번호</th>
                    <th scope="col" style={{width: "40%"}}>제목</th>
                    <th scope="col" style={{width: "15%"}}>작성자</th>
                    <th scope="col" style={{width: "27%"}}>작성일</th>
                    <th scope="col" style={{width: "10%"}}>댓글수</th>
                </tr>
            </thead>
            <tbody>
                {posts.length === 0 ? (
                    <tr>
                        <td colSpan="5" className="text-center">게시글이 없습니다.</td>
                    </tr>
                ) : (
                    posts.map(post => (
                        <tr key={post.postNo}>
                            <td>{post.postNo}</td>
                            <td><Link to={`/posts/detail/${post.postNo}`}>{post.title}</Link></td>
                            <td>{post.name}</td>
                            <td>{new Date(post.createdDate).toLocaleString()}</td>
                            <td>{post.commentCnt}</td>
                        </tr>
                    ))
                )}
            </tbody>
        </table>
    );
}

export default PostList;