<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringMVC02案例</title>
</head>
<body>

<!-- 第一个测试method="get" -->
<a href="${pageContext.request.contextPath}/test/helloGet">@RequestMapping的method方法get请求</a>
<!-- 测试method="post"请求 -->
<form action="test/helloPost" method="post">
	<input type="submit" value="method=post的请求">
</form>
<hr/>
<!-- 测试params的属性 -->
<a href="test/helloParams?money=1001">测试RequestMapping的params属性</a>

</body>
</html>