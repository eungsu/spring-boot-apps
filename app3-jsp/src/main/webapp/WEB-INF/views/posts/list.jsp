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
					<i class="bi bi-list"></i> 게시글 목록
				</div>
				<div class="card-body">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<form class="row row-cols-lg-auto g-3 align-items-center"
							action="/posts/list">
							<div class="col-12">
								<div class="input-group">
									<input class="form-control me-2" type="search" name="keyword"
										value="${param.keyword}"
										placeholder="검색" aria-label="Search">
								</div>
							</div>
							<div class="col-12">
								<button class="btn btn-outline-success" type="submit">검색</button>
							</div>
						</form>
				    	<a href="/posts/create" class="btn btn-primary">새 글 작성</a>
					</div>
					<div class="table-responsive">
						<table class="table table-hover table-striped border">
							<thead class="table-dark">
								<tr>
									<th scope="col" style="width: 8%;">#</th>
									<th scope="col" style="width: 50%;">제목</th>
									<th scope="col" style="width: 15%;">작성자</th>
									<th scope="col" style="width: 17%;">작성일</th>
									<th scope="col" style="width: 10%;">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="post" items="${posts }">
									<tr>
										<th scope="row">${post.no }</th>
										<td><a href="/posts/detail?no=${post.no }">${post.title }</a></td>
										<td>${post.user.nickname}</td>
										<td><fmt:formatDate value="${post.createdDate}" /></td>
										<td><fmt:formatNumber value="${post.viewCnt}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<c:if test="${paging.totalRows ne 0 }">
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
								<li class="page-item ${paging.first ? 'disabled' : '' }">
									<a class="page-link" 
										href="/posts/list?page=${paging.prevPage}&keyword=${param.keyword}" aria-label="Previous">
										<span aria-hidden="true">이전</span>
									</a>
								</li>
								
								<c:forEach var="pageNum" begin="${paging.beginPage }" end="${paging.endPage }">
									<li class="page-item ${paging.page eq pageNum ? 'active' : ''}">
									    <a class="page-link"
											href="/posts/list?page=${pageNum}&keyword=${param.keyword}">${pageNum }</a>
									</li>
								</c:forEach>
								
								<li class="page-item ${paging.last ? 'disabled' : '' }">
									<a class="page-link" 
										href="/posts/list?page=${paging.nextPage}&keyword=${param.keyword}" aria-label="Next">
										<span aria-hidden="true">다음</span>
									</a>
								</li>
							</ul>
						</nav>	
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>