<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Blood Donation Online System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
	<meta property="og:image" content="http://i.imgur.com/iCxl9rj.png" />
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="<c:url value="/resources/css/materialize.css" />" media="screen,projection"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />" media="screen,projection"/>
</head>
<body>
	<nav role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="<c:url value="/dashboard" />" class="brand-logo">Blood Donation Online System</a>
			
			<c:if test="${not empty userName}"> 
				<ul class="right hide-on-med-and-down">
					<li><a href="<c:url value="/dashboard" />">Home</a></li>
					<li><a href="<c:url value="/logout" />">Logout</a></li>
				</ul>
	
				<ul id="nav-mobile" class="side-nav">
					<li><a href="<c:url value="/dashboard" />">Home</a></li>
					<li><a href="<c:url value="/logout" />">Logout</a></li>
				</ul>
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
			</c:if>
		</div>
	</nav>

	<div class="container">
		<div class="facebook-share">
      		You can share this site to facebook by clicking the following link. <a href="#" onclick="facebookShare()">Share</a>
      		<a href="#" onclick="hidePromotion()" class="pull-right">Don't show this again</a>
    	</div>
	
		<jsp:doBody />

		<!-- Modal Structure -->
		<div id="confirmation-modal" class="modal">
			<div class="modal-content">
				<p id="confirmation-modal-message">Are you sure you want to continue with this action?</p>
				<a id="confirmation-modal-action">
					<button type="button" class="btn waves-effect waves-light grey">Continue</button>
				</a>
				<a class="waves-effect btn-flat" onclick="closeConfirmation();">Cancel</a>
			</div>
		</div>

		<br/> <br/>
	</div>

	<!-- Scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
	<script src="<c:url value="/resources/js/fbsdk.js" />"></script>
	<script src="<c:url value="/resources/js/materialize.js" />"></script>
	<script src="<c:url value="/resources/js/app.js" />"></script>
	
	<script>
		var message = "${infoMessage}" || "${errorMessage}";
		if (message) {
			Materialize.toast(message, 3500);	
		}
	</script>
</body>
</html>
