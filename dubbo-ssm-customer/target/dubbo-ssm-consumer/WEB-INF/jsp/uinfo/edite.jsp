<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form id="ff" action="../edite" target="_parent" method="post"
		modelAttribute="uentity">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>用户名</td>
				<td><form:input path="uname"/></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><form:input path="upass"/></td>
			</tr>
		</table>
		    <input type="submit" name="button" id="button" value="确认修改" />
	</form:form>
</body>
</html>