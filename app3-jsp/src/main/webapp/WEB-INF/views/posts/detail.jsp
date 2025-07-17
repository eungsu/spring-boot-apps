<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<c:set var="menu" value="posts" />
<%@ include file="../common/navbar.jsp" %>

<div class="container mt-4">
	<div class="post-header">
		<h1 class="post-title">${post.title }</h1>
		<div class="post-meta">
			<span><i class="bi bi-person-fill"></i> 작성자: <strong class="text-dark">${post.user.nickname}</strong></span>
			<span><i class="bi bi-calendar-check-fill"></i> 작성일: <fmt:formatDate value="${post.createdDate}" /></span>
			<span><i class="bi bi-eye-fill"></i> 조회수: <fmt:formatNumber value="${post.viewCnt}" /></span>
			<span><i class="bi bi bi-list"></i> 댓글수: <fmt:formatNumber value="${post.commentCnt}" /></span>
		</div>
	</div>
	<div class="post-content">
		<p>${markdown.renderHTML(post.content)}</p>
	</div>
	<div class="post-actions text-end">
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.user" var="user"/>
			<c:if test="${user.no eq post.userNo }">
				<a class="btn btn-warning" href="/posts/update?no=${post.no}" >
					<i class="bi bi-pencil-square"></i> 수정
				</a>
				<button class="btn btn-danger" onclick="confirmDelete(${post.no})">
					<i class="bi bi-trash-fill"></i> 삭제
				</button>
			</c:if>
		</sec:authorize>
		<a class="btn btn-secondary" href="/posts/list">
			<i class="bi bi-list"></i> 목록으로   
		</a>
	</div>
	
	<div class="comments-section">
		<h4>댓글 (<span>${post.commentCnt }</span>)</h4>
		<c:choose>
			<c:when test="${empty comments }">
				<div class="alert alert-info">
					등록된 댓글이 없습니다.
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<c:forEach var="comment" items="${comments }">
						<div class="comment-list">
							<div class="comment-meta">
								<strong><i class="bi bi-person-cicle"></i> <span>${comment.user.nickname}</span></strong>
								<span> | <i class="bi bi-calendar-check"> ${comment.createdDate}</i></span>
							</div>
							<div class="comment-content">
								<p>${comment.content}</p>
							</div>
							<sec:authorize access="isAuthenticated()">
								<c:if test="${user.no eq comment.userNo }">
									<div class="comment-actions">
										<button type="button" class="btn btn-sm btn-outline-danger"
											onclick="confirmCommentDelete(${comment.no}, ${post.no})">삭제</button>
									</div>
								</c:if>
							</sec:authorize>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
		<sec:authorize access="isAuthenticated()">
			<div class="comment-form mt-4">
				<h5>댓글 작성</h5>
				<form:form action="/posts/comments/add" method="post" id="comment-form">
					<input type="hidden" name="postNo" value="${post.no}" />
					<div class="mb-3">
						<textarea class="form-control"
							rows="3"
							name="content"
							placeholder="내용을 입력하세요"></textarea>
					</div>
					<div class="text-end">
						<button type="submit" class="btn btn-primary">댓글 작성</button>
					</div>
				</form:form>
			</div>
		</sec:authorize>
	</div>
</div>

<style>
	body {
		background-color: #f8f9fa; /* 밝은 회색 배경 */
	}
	.container {
		margin-top: 40px;
		margin-bottom: 60px;
		background-color: #ffffff;
		padding: 40px;
		border-radius: 8px;
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	}
	.post-header h1 {
		font-size: 2.2rem;
		font-weight: bold;
		margin-bottom: 10px;
		color: #343a40;
	}
	.post-meta {
		font-size: 0.9rem;
		color: #6c757d;
		border-bottom: 1px solid #e9ecef;
		padding-bottom: 15px;
		margin-bottom: 25px;
	}
	.post-meta span {
		margin-right: 15px;
	}
	.post-content {
		font-size: 1.1rem;
		line-height: 1.8;
		color: #495057;
		min-height: 200px; /* 최소 높이 설정 */
		margin-bottom: 40px;
	}
	.post-actions .btn {
		margin-right: 10px;
	}
	
	/* 댓글 섹션 스타일 */
	.comments-section {
		border-top: 1px solid #e9ecef;
		padding-top: 30px;
		margin-top: 40px;
	}
	.comment-item {
		border: 1px solid #dee2e6;
		border-radius: 5px;
		padding: 15px;
		margin-bottom: 15px;
		background-color: #f8f9fa;
	}
	.comment-meta {
		font-size: 0.85rem;
		color: #6c757d;
		margin-bottom: 5px;
	}
	.comment-meta strong {
		color: #495057;
	}
	.comment-content {
		font-size: 1rem;
		line-height: 1.6;
		color: #343a40;
	}
	.comment-form textarea {
		resize: vertical; /* 세로 크기 조절 허용 */
		min-height: 100px;
	}
	.comment-actions {
		text-align: end;
		margin-top: 10px;
	}
	.error-message {
		color: #dc3545;
		font-size: 0.875em;
		margin-top: 0.25rem;
	}
</style>

<sec:authorize access="isAuthenticated()">
	<c:if test="${user.no eq post.userNo }">
		<script type="text/javascript">
			function confirmDelete(postNo) {
				if (confirm("이 게시글을 삭제하시겠습니까?")) {
					location.href = `/posts/delete?no=\${postNo}`;
				}
			}
		</script>
	</c:if>
	<script type="text/javascript">
		function confirmCommentDelete(commentNo, postNo) {
			if (confirm("이 댓글을 삭제하시겠습니까?")) {
				location.href = `/posts/comments/delete?commentNo=\${commentNo}&postNo=\${postNo}`;
			}
		}
	</script>
</sec:authorize>

<%@ include file="../common/footer.jsp" %>