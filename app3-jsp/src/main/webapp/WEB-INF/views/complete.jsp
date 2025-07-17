<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<c:set var="menu" value="signup"/>
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="success-container">
		<i class="bi bi-check-circle-fill success-icon"></i>

		<h2 class="success-title">회원가입이 완료되었습니다!</h2>
		<p class="success-message">
			<span>${username }</span>님, 회원가입이 완료되었습니다.<br>
			이제 로그인을 통해 모든 기능을 이용하실 수 있습니다.
		</p>

		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
			<a href="/login" class="btn btn-success btn-lg btn-action">로그인</a>
			<a href="/" class="btn btn-outline-secondary btn-lg btn-action">메인</a>
		</div>
	</div>
</div>

<style>
	body { background-color: #f8f9fa; }
	.success-container {
		max-width: 600px; margin: 80px auto; padding: 40px;
		background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
		text-align: center;
	}
	.success-icon { font-size: 4rem; color: #28a745; margin-bottom: 20px; }
	.success-title { color: #343a40; margin-bottom: 15px; }
	.success-message { color: #6c757d; margin-bottom: 30px; line-height: 1.6; }
	.btn-action { width: 180px; padding: 12px 20px; margin: 0 10px; }
</style>
<%@ include file="common/footer.jsp" %>