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
	  <h3>게시글 수정</h3>
		<c:if test="${board != null}">
			<form action="/board/modify" method="post">
			    <div class="form-group">
			      <label for="num">번호</label>
			      <input type="text" class="form-control" id="bno" name="bno" readonly value="${board.bno}">
			    </div>
			    <div class="form-group">
			      <label for="title">제목</label>
			      <input type="text" class="form-control" id="title" name="title" value="${board.title}">
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
			      <label for="content">내용</label>
			      <textarea class="form-control" id="content" name="content">${board.content}</textarea>
			    </div>
			    <button type="submit" class="btn btn-outline-info">수정</button>
		    </form>
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
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>