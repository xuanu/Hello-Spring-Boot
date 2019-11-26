<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>我的名字是：${username} </p>
<% for(int i=0;i<100;i++){ %>
<p>郑治玄  </p> <%=i%>
<%} %>
</body>
</html>