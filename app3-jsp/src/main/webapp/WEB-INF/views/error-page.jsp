<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					에러 발생
				</div>
				<div class="card-body">
					<p class="display-4">상태 코드 : <span class="text-danger">${errorCode}</span></p>
					<p >오류 메세지 : <span class="error-message">${errorMessage}</span></p>
					<div>
						<a href="/" class="btn btn-primary">홈으로 이동</a>
						<button onclick="history.back()" class="btn btn-outline-secondary">이전 페이지로</button>
						<c:if test="${errorCode eq '500' }">
							<button onclick="location.reload()" class="btn btn-outline-secondary">다시 시도</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jsp" %>