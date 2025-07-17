<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">스프링부트 게시판</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-support-content"
			aria-controls="navbar-support-content" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbar-support-content">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link ${menu eq 'home' ? 'active fw-bold' : ''}"
					   href="/">홈</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${menu eq 'posts' ? 'active fw-bold' : ''}" 
					   href="/posts/list">게시글</a>
				</li>
			</ul>
			<sec:authorize access="isAuthenticated()">
			<span class="navbar-text">
				<sec:authentication property="principal.user.nickname" />
			</span>
			</sec:authorize>
			<ul class="navbar-nav mb-2 mb-lg-0">
				<sec:authorize access="isAnonymous()">
				<li class="nav-item">
					<a class="nav-link ${menu eq 'login' ? 'active fw-bold' : ''}" 
					   href="/login">로그인</a>
				</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<form:form class="d-inline" method="post" action="logout" >
						<button type="submit" class="btn btn-link nav-link">로그아웃</button>
					</form:form>
				</li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
				<li class="nav-item">
					<a class="nav-link  ${menu eq 'signup' ? 'active fw-bold' : ''}" 
					   href="signup">회원가입</a>
				</li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>