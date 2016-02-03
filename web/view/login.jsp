<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<title>Trailer Rental</title>
</head>
<body>
	<div id="container">
		<div id="head-image">
		<img alt="Trailer Rental" src="images/anhangerklein.jpg" title="Trailer Rental" width="150" height="50"/>
		</div>
		<div id="header">
			<h1>Trailer Rental Shop</h1>
		</div>
	<jsp:include page="customer-menu.htm" />
		<div id="content">
			<h2>
				<c:out value="${title}"></c:out>
			</h2>
			<c:out value="${errorMessage}"></c:out>
			<form action="login-check.html" method="post">
				<table>
					<tr>
						<td><c:out value="${username}"></c:out></td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td><c:out value="${password}"></c:out></td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td><div id="small">Not registered? <br/><a href="create-customer.html">Register here.</a></div></td>
						<td colspan="1"><input type="submit" value="Login" class="button"/></td>
						
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">
			<h4>&copy;Trailer Rental Shop 2013</h4>
		</div>
	</div>
</body>
</html>