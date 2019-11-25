<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
 <script type="text/javascript" src="/resource/kindeditor/kindeditor-all.js"></script>
 <title>有花堪折直须折</title>
 
<body style="background:#3399FF;">

<!-- 导航条 -->
<nav class="navbar navbar-default" style="background:#000099">
	<%@include  file="../common/top.jsp" %>
</nav>
<div class="container" style="margin-top:30px;margin-bottom:50px">
	<div class="row">
		<div class="col-md-3" >
			<ul class="list-group homemenu">
			 <li class="list-group-item list-group-item-success" data="/user/myarticles">我的文章</li>
			 <li class="list-group-item list-group-item-info" data="/user/postArticle">发布文章</li>
			 <li class="list-group-item list-group-item-info" data="/user/postImg">发布图片</li>
			 <li class="list-group-item list-group-item-info" data="/collect/list">我的收藏</li>
			 <li class="list-group-item list-group-item-info">我的评论</li>
			 <li class="list-group-item list-group-item-info">投票管理</li>
			 <li class="list-group-item list-group-item-info">个人设置</li>
			</ul>
		</div>
		<div class="col-md-9" id="content" 
		   style="border:solid;min-height:500px;background-color:#FFFFFF;
		   overflow-x:scroll;overflow-y:scroll
		   ">
		
				<div id="kindEditor" style="display: none">
				   <!-- 引入kindEditor的样式 -->
				  <jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
              </div>
              
			
		</div>
	</div>
</div>

<nav class="navbar navbar-default navbar-fixed-bottom" style="background:#000099">
	<%@include file="../common/footer.jsp"  %>
</nav>

<script type="text/javascript">
	$(".homemenu li").click(function(){
		var url  = $(this).attr("data");
		//alert(url);
		$("#content").load(url);
	})
	
	var url = $(".homemenu li:eq(0)").attr("data");
	$("#content").load(url);
	
</script>

</body>
</html>