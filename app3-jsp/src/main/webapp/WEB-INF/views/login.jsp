<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<c:set var="menu" value="login" />
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					로그인
				</div>
				<div class="card-body">
					<c:if test="${param.error eq 'fail' }">
						<div class="alert alert-danger" role="alert">
							<strong>로그인 실패</strong> 아이디 혹은 비밀번호를 확인하시기 바랍니다.
						</div>
					</c:if>
					<c:if test="${param.error eq 'required' }">
						<div class="alert alert-danger" role="alert">
							<strong>인증 필요</strong> 로그인 후 이용가능한 요청입니다.
						</div>
					</c:if>
					<form:form cssClass="border p-3" action="/login" method="post">
						<div class="mb-3">
							<label for="username" class="form-label">아이디</label>
							<input type="text" class="form-control" name="username" placeholder="아이디를 입력하세요"/>
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">비밀번호</label>
							<input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
						</div>
						<button type="submit" class="btn btn-primary w-100 mb-3">로그인</button>
						<a href="/signup" class="btn btn-outline-secondary w-100 mb-3">회원가입</a>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jsp" %>