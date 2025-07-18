<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<c:set var="menu" value="signup"/>
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					회원가입 완료
				</div>
				<div class="card-body">
					<p class="card-text">
						<span>${username }</span>님, 회원가입이 완료되었습니다.<br>
							이제 로그인을 통해 모든 기능을 이용하실 수 있습니다.
					</p>
					<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<a href="/login" class="btn btn-success btn-lg ">로그인</a>
						<a href="/" class="btn btn-outline-secondary btn-lg ">메인</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jsp" %>