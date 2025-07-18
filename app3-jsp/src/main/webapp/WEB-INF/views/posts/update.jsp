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
					<i class="bi bi-pencil-square me-2"></i> 게시글 수정
				</div>
				<div class="card-body">
					<form:form cssClass="border p-3" action="/posts/update?no=${param.no }" method="post" novalidate="novalidate"
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
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>