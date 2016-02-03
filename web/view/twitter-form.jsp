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
<script type="text/javascript">
	function doSubmit() {
		var tweetText = document.getElementById("tweetsText").value;
		if (tweetText == '') {
			alert('Twitter post can\'t be blank');
			return false;
		} else {
			return true;
		}
	}
</script>
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
				<img src="images/twitter_logo.jpg" alt="twitter logo" width = "75" height = "40"/><c:out value="${title}" />
			</h2>
			<h4 style="color: green">
				<c:out value="${success}" />
			</h4>
			
			<form action="post-tweet.html" method="post" onsubmit="return doSubmit()">
				<table>
					<tr>
						<td valign="top">Tweets Text:</td>
						<td><textarea rows="10" cols="40" name="tweetsText" id="tweetsText"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"
							value="<c:out value='${button}'/>" class="button" /></td>
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