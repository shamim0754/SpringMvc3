<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script type="text/javaScript">


 var pass="admin";
 password=prompt('Please enter password','');
 if (pass != password){
 	 alert('Wrong Password!');
     window.location="	";  
 }

</script>
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
		
				<jsp:include page="admin-menu.htm" />
		
		
			
			
		<div id="footer">
			<h4>&copy;Trailer Rental Shop 2013</h4>
		</div>
	
	</div>
	
</body>
</html>
