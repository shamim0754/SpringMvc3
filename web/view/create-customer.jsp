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
		<c:choose>
		<c:when test="${sessionScope.admin}">
		<jsp:include page="admin-menu.htm" />
		</c:when>
		<c:otherwise>
		<jsp:include page="customer-menu.htm" />
		</c:otherwise>
		</c:choose>
		<div id="content">
		<h2><c:out value="${title}"/></h2>
		<c:out value="${errorMessage}"/>
		<form action="saveCustomer.html" method="post">
		<table>
		<tr>
			<td>First Name:</td>
			<td>
			<input type="hidden" name="customerId" value="<c:out value="${customer.customerId}"></c:out>"/>
			<input type="text" name="firstName" value="<c:out value="${customer.firstName}"></c:out>"/></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lastName" value="<c:out value="${customer.lastName}"></c:out>"/></td>
		</tr>
		<tr>
			<td>Street:</td>
			<td><input type="text" name="street" value="<c:out value="${customer.street}"></c:out>"/></td>
		</tr>
			<tr>
			<td>Zip Code:</td>
			<td><input type="text" name="zipCode" value="<c:out value="${customer.zipCode}"></c:out>"/></td>
		</tr>
			<tr>
			<td>Place:</td>
			<td><input type="text" name="place" value="<c:out value="${customer.place}"></c:out>"/></td>
		</tr>
		<tr>
			<td>License Category:</td>
			<td><select name="licenseCategory" >
			<option value="B" <c:if test="${customer.licenseCategory=='B'}">selected</c:if> >B</option>
			<option value="BE" <c:if test="${customer.licenseCategory=='BE'}">selected</c:if>>BE</option>
			<option value="B96" <c:if test="${customer.licenseCategory=='B96'}">selected</c:if>>B96</option>
			</select></td>
		</tr>
		<tr>
			<td>User Name:</td>
			<td><input type="text" name="userName" value="<c:out value="${customer.userName}"></c:out>"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password" value="<c:out value="${customer.password}"></c:out>"/></td>
		</tr>
		<tr>
			<td colspan="2">
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