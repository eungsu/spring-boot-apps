<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="error-container">
		<h1 class="error-code">${errorCode }</h1>
		<p class="error-message">${errorMessage }</p>
		<div>
			<a href="/" class="btn btn-primary">홈으로 이동</a>
			<button onclick="history.back()" class="btn btn-outline-secondary">이전 페이지로</button>
			<c:if test="${errorCode eq '500' }">
				<button onclick="location.reload()" class="btn btn-outline-secondary">다시 시도</button>
			</c:if>
		</div>
	</div>
</div>

<style>
	body {
		background-color: #f8f9fa; /* 밝은 회색 배경 */
	}
	.error-container {
		margin: 0 auto;
		text-align: center;
		padding: 3rem;
		background-color: #ffffff;
		border-radius: 0.5rem;
		box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
		max-width: 500px;
		width: 90%;
	}
	.error-code {
		font-size: 6rem;
		font-weight: bold;
		color: #dc3545; /* Bootstrap primary color */
		margin-bottom: 1rem;
	}
	.error-message {
		font-size: 1.5rem;
		color: #6c757d; /* Bootstrap secondary color */
		margin-bottom: 2rem;
	}
	.btn-primary {
		margin: 0 0.5rem;
	}
</style>
<%@ include file="common/footer.jsp" %>