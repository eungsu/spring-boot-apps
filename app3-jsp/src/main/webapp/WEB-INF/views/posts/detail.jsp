<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<c:set var="menu" value="posts" />
<%@ include file="../common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<i class="bi bi-book"></i> 게시글 상세 정보
				</div>
				<div class="card-body">
					<div class="mb-3">
						<h1>${post.title }</h1>
						<div>
							<span><i class="bi bi-person-fill"></i> 작성자: <strong class="text-dark">${post.user.nickname}</strong></span>
							<span><i class="bi bi-calendar-check-fill"></i> 작성일: <fmt:formatDate value="${post.createdDate}" /></span>
							<span><i class="bi bi-eye-fill"></i> 조회수: <fmt:formatNumber value="${post.viewCnt}" /></span>
							<span><i class="bi bi bi-list"></i> 댓글수: <fmt:formatNumber value="${post.commentCnt}" /></span>
						</div>
					</div>
					<hr>
					<div class="card-text mb-3">
						<p>${markdown.renderHTML(post.content)}</p>
					</div>
					<div class="text-end mb-3">
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
					<div class="mb-3">
						<h4>댓글 (<span>${post.commentCnt }</span>)</h4>
						<c:choose>
							<c:when test="${empty comments }">
								<div class="alert alert-info small">
									등록된 댓글이 없습니다.
								</div>
							</c:when>
							<c:otherwise>
								<div class="small">
									<c:forEach var="comment" items="${comments }">
										<div class="mb-3">
											<div class="mb-1">
												<strong><i class="bi bi-person-cicle"></i> <span>${comment.user.nickname}</span></strong>
												<span> | <i class="bi bi-calendar-check"></i> <fmt:formatDate value="${comment.createdDate}" /></span>
											</div>
											<div class="card-text">
												<p>${comment.content}</p>
											</div>
											<sec:authorize access="isAuthenticated()">
												<c:if test="${user.no eq comment.userNo }">
													<div class="text-end">
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
							<div class="mt-4">
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
			</div>
		</div>
	</div>
</div>

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