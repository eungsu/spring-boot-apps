<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<c:set var="menu" value="signup" />
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					회원가입
				</div>
				<div class="card-body">
					<form:form cssClass="border p-3" action="/signup" method="post" novalidate="novalidate" modelAttribute="signupForm">
						<div class="mb-3">
							<form:label path="username" class="form-label"><span class="text-danger">*</span> 아이디</form:label>
							<form:input
								cssClass="${validated ? (errors.hasFieldErrors('username') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}" 
								path="username"
								placeholder="아이디를 입력하세요"/>
							<form:errors path="username" cssClass="invalid-feedback"/>
						</div>
						<div class="mb-3">
							<form:label path="password" class="form-label"><span class="text-danger">*</span> 비밀번호</form:label>
							<form:password  
								cssClass="${validated ? (errors.hasFieldErrors('password') ? 'form-control is-invalid' : 'form-control is-valid') : 'form-control'}"
								path="password" 
								placeholder="비밀번호를 입력하세요" />
							<form:errors path="password" cssClass="invalid-feedback"/>
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
		</div>
	</div>
</div>

<%@ include file="common/footer.jsp" %>