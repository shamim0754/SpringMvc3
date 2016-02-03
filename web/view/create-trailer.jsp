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
		<jsp:include page="admin-menu.htm" />
		<div id="content">
		<h2><c:out value="${title}"/></h2>
		
		<form action="saveTrailer.html" method="post">
		<table>
		<tr>
			<td>Title:</td>
			<td>
			<input type="hidden" name="trailerId" value="<c:out value="${trailer.trailerId}"></c:out>"/>
			<input type="text" name="title" value="<c:out value="${trailer.title}"></c:out>"/></td>
		</tr>
		<tr>
			<td>Carrying Capacity(In kg):</td>
			<td><input type="text" name="carryingCapacity" value="<c:out value="${trailer.carryingCapacity}"></c:out>"/></td>
		</tr>
		<tr>
			<td>Rental Price(Per Day):</td>
			<td><input type="text" name="dailyFee" value="<c:out value="${trailer.dailyFee}"></c:out>"/></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="hidden" name="id" value="<c:out value="${trailer.status}"></c:out>"/>
			<input type="submit" value="<c:out value='${button}'/>" class="button"/>
			</td>	
					
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