<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>页面添加</title>
</head>
<body>
	<form:form id="addForm" method="post" action="add"
		>
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" id="uname" name="uname" /></td>
			</tr>

			<tr>
				<td>用户密码</td>
				<td><input type="text" id="upass" name="upass" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="button" id="button" value="提交" />
				</td>
				<td></td>
			</tr>
		</table>

	</form:form>
</body>
</html>