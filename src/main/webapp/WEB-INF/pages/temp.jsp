<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	String url=request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="<%= url %>/temp/hello" method="post">
		输入你的名字:<input type="text" name="name" />
		<input type="submit" value="submit" />
	</form>
	<br/>
	from session:${ sessionScope.name }
	<br/>
	from model: ${ welcome }
</body>
</html>