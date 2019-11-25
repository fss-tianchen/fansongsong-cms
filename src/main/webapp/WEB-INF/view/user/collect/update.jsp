<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form modelAttribute="collect" id="form">
	名称： <form:input path="name"/><form:errors path="name"/>
	地址： <form:input path="url"/><form:errors path="url"/>
	<form:hidden path="id" />
	<input type="button" value="提交" onclick="submitData()">
	
</form:form>
<script>
	function submitData(){
		//$("#form").submit();
		
		$.post('/collect/update',$("#form").serialize(),
				function(html){
					$("#content").html(html);
			
			}
			);
		
	}
</script>
    