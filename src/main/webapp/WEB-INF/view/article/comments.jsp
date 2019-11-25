<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:forEach items="${pageComment.list}" var="comment" >
	<div class="row">
		${comment.content}
		<br>
		${comment.userId}  &nbsp;&nbsp;&nbsp;&nbsp;
		<fmt:formatDate value="${comment.created}" pattern="yyy-MM-dd HH:mm:ss"/>
		
	</div>
</c:forEach>    