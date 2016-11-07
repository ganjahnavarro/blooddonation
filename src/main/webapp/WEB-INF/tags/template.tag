<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Blood Donation System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="<c:url value="/resources/css/materialize.css" />" media="screen,projection"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />" media="screen,projection"/>
</head>
<body>
	<nav role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="<c:url value="/dashboard" />" class="brand-logo">Blood Donation Online System</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="<c:url value="/dashboard" />">Home</a></li>
				<li><a href="<c:url value="/logout" />">Logout</a></li>
			</ul>

			<ul id="nav-mobile" class="side-nav">
				<li><a href="<c:url value="/dashboard" />">Home</a></li>
				<li><a href="<c:url value="/logout" />">Logout</a></li>
			</ul>
			<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
		</div>
	</nav>

	<div class="container">
		<!-- Message -->
		<c:choose>
			<c:when test="${not empty infoMessage}">
				<c:out value="${infoMessage}"></c:out>
			</c:when>
			
			<c:when test="${not empty errorMessage}">
				<c:out value="${errorMessage}"></c:out>
			</c:when>
		</c:choose>
	
		<jsp:doBody />
		
		<br/> <br/>
	</div>

	<!-- Scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/materialize.js" />"></script>
	<script src="<c:url value="/resources/js/app.js" />"></script>
</body>
</html>
