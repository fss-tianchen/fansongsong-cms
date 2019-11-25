<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="" method="post" modelAttribute="url" >
		<form:input type="text" path="url" name="url" />
		<form:errors path="url"/>
		<br/>
		<form:input type="text" path="name" name="name"/>
		<form:errors path="name"/>
		<button>提交</button>
	</form:form>
</body>
</html>