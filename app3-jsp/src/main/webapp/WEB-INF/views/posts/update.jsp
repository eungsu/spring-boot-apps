<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<c:set var="menu" value="posts" />
<%@ include file="../common/navbar.jsp" %>

<div class="container mt-4">
	<h1><i class="bi bi-pencil-square me-2"></i> 게시글 수정</h1>
	<hr class="mb-4">

	<form:form action="/posts/update?no=${param.no }" method="post" class="needs-validation" novalidate="novalidate"
		modelAttribute="postUpdateForm" >
		<div class="mb-3">
			<form:label path="title" class="form-label">제목</form:label>
			<form:input
				cssClass="${validated ? (errors.hasFieldErrors('title') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
				path="title" 
				placeholder="게시글 제목을 입력하세요."/>
			<form:errors path="title" cssClass="invalid-feedback"/>
		</div>
		<div class="mb-4">
			<form:label path="content" class="form-label">내용</form:label>
			<form:textarea
				cssClass="${validated ? (errors.hasFieldErrors('content') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
				path="content" 
				placeholder="게시글 내용을 입력하세요."></form:textarea>
			<form:errors path="content" cssClass="invalid-feedback"/>
			<div class="form-text">내용은 마크다운형식으로 작성하세요.</div>
		</div>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-primary btn-lg">
				<i class="bi bi-send-fill me-2"></i> 수정
			</button>
			<a href="/posts/detail?no=${param.no }" class="btn btn-secondary btn-lg">
				<i class="bi bi-x-circle-fill me-2"></i> 취소
			</a>
		</div>
	</form:form>
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
	h1 {
		color: #343a40;
		margin-bottom: 30px;
		text-align: center;
		font-weight: bold;
	}
	textarea.form-control {
		min-height: 300px; /* 게시글 내용 입력 필드의 최소 높이 */
		resize: vertical; /* 세로 방향으로만 크기 조절 허용 */
	}
	.form-label {
		font-weight: 600; /* 레이블 글꼴 굵게 */
	}
</style>
<%@ include file="../common/footer.jsp" %>