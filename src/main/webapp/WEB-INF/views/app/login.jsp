<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
	<title>Blood Donation Online System</title>
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="<c:url value="/resources/css/materialize.css" />" media="screen,projection"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />" media="screen,projection"/>
	
	<style>
		html {background-image: url("<c:url value="/resources/images/pattern.png" />")}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col s12 offset-m4 m4">
				<div class="page-header center">
					<img src="<c:url value="/resources/images/volunteering.png" />" />
					<h5 class="">Blood Donation Online System</h5>
				</div>
				<div class="card">
					<form class="card-content" action="<c:url value='/login' />" method="post">
						<div class="row">
							<div class="input-field col s12">
								<input id="userName" name="userName" type="text" class="validate" required>
								<label for="userName" class="active" data-error="Invalid email.">Email</label>
							</div>
							<div class="input-field col s12">
								<input id="password" name="password" type="password" class="validate" required>
								<label for="password" class="active" data-error="Minimum length, 6 characters">Password</label>
							</div>
						</div>
						
						<div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
							<button class="btn block waves-effect waves-light" type="submit" name="action">Login</button>
						</div>
					</form>
				</div>
				
				<div class="card">
					<div class="card-content">
						<a href="<c:url value="/donor/new" />">
							<button class="btn grey block waves-effect waves-light" type="submit" name="action">I want to donate</button>
						</a>
					</div>
				</div>
				
				<div class="card">
					<div class="card-content">
						<a href="<c:url value="/patient/new" />">
							<button class="btn grey block waves-effect waves-light" type="submit" name="action">I need donation</button>
						</a>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.tablesorter.js" />"></script>
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
