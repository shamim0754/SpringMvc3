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
		<h2>
			<c:out value="${title}" />
		</h2>
		<table>
		<tbody>
			<tr>
			<td>Title</td>
			<td>Carrying capacity(In kg)</td>
			<td>Rental Price(Per day)</td>
			<td>Status</td>
			<c:if test="${edit}">
			<td></td>
			<td></td>
			</c:if>
			</tr>
		
		<c:forEach items="${trailerList}" var="trailer">
			<tr>
				<td>${trailer.title}</td>
				<td>${trailer.carryingCapacity}</td>
				<td>${trailer.dailyFee}</td>
				<c:if test="${trailer.status==0}"><td><c:out value="available"/></td></c:if>
				<c:if test="${trailer.status!=0}"><td><c:out value="rented"/></td></c:if>
				<c:if test="${edit}">
					<td><a href="create-trailer.html?id=<c:out value="${trailer.trailerId}"/>">
								<img src="images/button_edit.png" alt="edit" />
						</a>
					</td>
					<td><a href="delete-trailor.html?id=<c:out value="${trailer.trailerId}"/>"> 
								<img src="images/button_drop.png" alt="delete" />
						</a>
					</td>		
					
							
			</c:if>
			</tr>
		</c:forEach>
		</tbody>
		</table>
		
		
		</div>
		<div id="footer">
			<h4>&copy;Trailer Rental Shop 2013</h4>
		</div>
	</div>	

</body>
</html>