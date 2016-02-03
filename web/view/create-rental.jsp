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
			<h1>Trailer Rental</h1>
		</div>
		<jsp:include page="customer-menu.htm" />
		<div id="content">
		<h2><c:out value="${title}"/></h2>
		
		<form action="saveRent.html" method="post">
		<table>
		<tr>
			<td>Name:</td>
			<td>
			<input type="hidden" name="customerId" value="<c:out value="${customerId}"/>"/>
			<c:out value="${customerName}"/>
			</td>
		</tr>
		<tr>
			<td>Trailers Available:</td>
			<td><select name="trailerId" >
			 <c:forEach items="${trailerList}" var="trailer">
			<option value="${trailer.trailerId}"><c:out value="${trailer.title}"/></option>
			</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="Rent Trailer" class="button"/>
			</td>				
		</tr>
		</table>
		<div id="small"><p><br /><c:out value="${message}"/></p></div>
		</form>
		</div>
		
		
		<div id="footer">
			<h4>&copy;Trailer Rental Shop 2013</h4>
		</div>
		
	</div>

</body>
</html>