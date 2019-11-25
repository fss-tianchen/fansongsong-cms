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
 <title>${article.title}</title>
</head>
<body>
	<div class="container">
		<h2>
			${article.title}
		</h2>
		<h5>
			作者：${article.user.username} 
			&nbsp;&nbsp;&nbsp;&nbsp; 发布时间：${article.created} 
			&nbsp;&nbsp;&nbsp;&nbsp; 频道：${article.channel.name} 
			&nbsp;&nbsp;&nbsp;&nbsp; 分类：${article.category.name} 
			<%-- <a href="javascript:favarite(${article.id})">收藏</a>
			&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="javascript:collect(${article.id})">加入我的收藏夹</a> --%>
		</h5>
		<div>
			${article.content}
		</div>
		<div>
			<nav aria-label="...">
			  <ul class="pager">
			    <li><a href="#">上一篇</a></li>
			    <li><a href="#">下一篇</a></li>
			  </ul>
			</nav>
		</div>
		<div>
			<!-- 	显示文章的评论 -->
			<div class="row">
				<textarea rows="5" cols="100%"  id="commentContent">
				
				</textarea>
				<input type="button" onclick="comment()" value="发表评论">
			</div>
			<div class="container" id="commentList">
			
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
	/* function favarite(id){
		
		$.post("/user/favarite",{id:id},function(msg){
			if(msg.result==1){
				alert('收藏成功')
			}else{
				alert(msg.errorMsg);
			}
		},"json");
		
	} */
	
	// 将本篇放入我的收藏夹中
	/* function collect(id){
		
		 var url = window.location.href;
		 
		$.post("/user/collect",{name:'${article.title}',url:url},function(msg){
			if(msg.result==1){
				alert('加入收藏夹成功')
			}else{
				alert(msg.errorMsg);
			}
		},"json");
		
	} */
	
	
	function comment(){
		$.post("/user/comment",{id:'${article.id}',content:$("#commentContent").val()},
			function(msg){
				if(msg.result==1){
					alert('评论成功')
				}else{
					alert(msg.errorMsg);
				}
		},
		"json"
		)
	}
	
	function showComments(){
		$("#commentList").load('/article/commentlist?id=${article.id}');
	}
	showComments();
	
	</script>

</body>
</html>