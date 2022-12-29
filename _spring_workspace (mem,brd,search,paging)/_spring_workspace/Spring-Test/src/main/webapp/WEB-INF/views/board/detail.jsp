<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
	<div class="container">
	  <h3>게시글 상세</h3>
		<c:if test="${board != null}">
		    <div class="form-group">
		      <label for="bno">번호</label>
		      <input type="text" class="form-control" id="bno" name="bno" readonly value="${board.bno}">
		    </div>
		    <div class="form-group">
		      <label for="title">제목</label>
		      <input type="text" class="form-control" id="title" name="title" readonly value="${board.title}">
		    </div>
		    <div class="form-group">
		      <label for="writer">작성자</label>
		      <input type="text" class="form-control" id="writer" name="writer" readonly value="${board.writer}">
		    </div>
		    <div class="form-group">
		      <label for="registerDate">작성일</label>
		      <input type="text" class="form-control" id="registerDate" name="registerDate" readonly value="${board.registerDate}">
		    </div>
		    <div class="form-group">
		      <label for="content">조회수</label>
		      <textarea class="form-control" id="content" name="read_count" readonly>${board.read_count}</textarea>
		    </div>
		    <div class="form-group">
		      <label for="content">내용</label>
		      <textarea class="form-control" id="content" name="content" readonly>${board.content}</textarea>
		    </div>
		</c:if>
		<c:if test="${board == null}">
		<h2> 존재하지 않은 게시물입니다. </h2>
		</c:if>
		<a href="/board/list">
		<button type="button" class="btn btn-outline-info">목록</button>
		</a>
		<a href="/board/register">
		<button type="button" class="btn btn-outline-info">글쓰기</button>
		</a>
		<!-- 로그인 id와 작성자 id가 같지 않으면 수정, 삭제버튼 안보이게... -->
		<c:if test="${ses != null && ses.id == board.writer}">
			<a href="/board/modify?bno=${board.bno}">
			<button type="button" class="btn btn-outline-info">수정</button>
			</a>
			<a href="/board/remove?bno=${board.bno}">
			<button type="button" class="btn btn-outline-info">삭제</button>
			</a>
		</c:if>
	</div>
	<!-- comment line -->
	<!-- <div class="container">
		<div class="input-group my-3">
			<span class="input-group-text" id="cmtWriter">Anonymous</span>
			<input type="text" class="form-control" id="cmtText" value="Test Add Comment ">
			<button class="btn btn-success" id="cmtPostBtn" type="button">Post</button>
		</div>
		<ul class="list-group list-group-flush" id="cmtListArea">
		  <li class="list-group-item d-flex justify-content-between align-items-start">
		    <div class="ms-2 me-auto">
		      <div class="fw-bold">Writer</div>
		      Content for Comment
		    </div>
		    <span class="badge bg-dark rounded-pill">modAt</span>
		  </li>
		</ul> -->

	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>