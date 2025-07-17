<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<c:set var="menu" value="signup" />
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="col-md-6 col-lg-4 login-container">
		<h2 class="text-center mb-4">회원가입</h2>
    
		<form:form action="/signup" method="post" novalidate="novalidate" modelAttribute="signupForm">
			<div class="mb-3">
				<form:label path="username" class="form-label"><span class="text-danger">*</span> 아이디</form:label>
				<form:input
					cssClass="${validated ? (errors.hasFieldErrors('username') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}" 
					path="username"
					placeholder="아이디를 입력하세요"/>
				<form:errors path="username" cssClass="invalid-feedback"/>
				<div class="form-text">영어 대소문자, 숫자 포함 3~20 글자</div>
			</div>
			<div class="mb-3">
				<form:label path="password" class="form-label"><span class="text-danger">*</span> 비밀번호</form:label>
				<form:password  
					cssClass="${validated ? (errors.hasFieldErrors('password') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
					path="password" 
					placeholder="비밀번호를 입력하세요" />
				<form:errors path="password" cssClass="invalid-feedback"/>
				<div class="form-text">영어 대소문자, 숫자 최소 하나 이상 포함 8~20 글자</div>
			</div>
			<div class="mb-3">
				<form:label path="email" class="form-label"><span class="text-danger">*</span> 이메일</form:label>
				<form:input
					cssClass="${validated ? (errors.hasFieldErrors('email') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
					path="email" 
					placeholder="이메일을 입력하세요" />
				<form:errors path="email" cssClass="invalid-feedback"/>
			</div>
			<div class="mb-3">
				<form:label path="nickname" class="form-label">이름</form:label>
				<form:input
					cssClass="${validated ? (errors.hasFieldErrors('nickname') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
					path="nickname" 
					placeholder="이름을 입력하세요" />
				<form:errors path="nickname" cssClass="invalid-feedback"/>
				<div class="form-text">한글 2~7 글자</div>
			</div>
			<div class="mb-3">
				<form:label path="tel" class="form-label">전화번호</form:label>
				<form:input
					cssClass="${validated ? (errors.hasFieldErrors('tel') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
					path="tel" 
					placeholder="전화번호를 입력하세요" />
				<form:errors path="tel" cssClass="invalid-feedback"/>
			</div>
			<button type="submit" class="btn btn-primary w-100 mb-3">회원가입</button>
			<a href="/login" class="btn btn-outline-secondary w-100 mb-3">로그인</a>
		</form:form>
	</div>
</div>

<style>
	body {
		background-color: #f8f9fa; /* 밝은 회색 배경 */
	}
	.login-container {
		max-width: 600px; /* 로그인 폼의 최대 너비 */
		margin: 40px auto; /* 위아래 100px 마진, 좌우 자동 마진으로 중앙 정렬 */
		padding: 30px;
		border-radius: 8px;
		box-shadow: 0 4px 8px rgba(0,0,0,0.1);
		background-color: #ffffff;
	}
</style>

<%@ include file="common/footer.jsp" %>