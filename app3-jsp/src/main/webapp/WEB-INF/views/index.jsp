<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<c:set var="menu" value="home" />
<%@ include file="common/navbar.jsp" %>

<div class="container mt-4">
	<div class="row mb-3">
		<div class="col-12">
			<div class="p-5 mb-4 bg-light rounded-3">
				<div class="container-fluid py-5 text-center">
		  			<h1 class="display-5 fw-bold">샘플 애플리케이션</h1>
						<p class="col-md-8 fs-4 mx-auto">Spring Boot, Spring Security, mybatis, H2, JSP를 활용한 게시판 웹 애플리케이션입니다.</p>
						<sec:authorize access="isAnonymous()">
							<hr>
							<a class="btn btn-primary btn-lg mt-3"
							   href="/signup" >회원가입</a>
							<a class="btn btn-outline-primary btn-lg mt-3 ms-2"
							   href="/login" >로그인</a>
						</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jsp" %>