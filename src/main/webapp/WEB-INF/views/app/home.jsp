<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Blood Donation Online System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />

	<link rel="stylesheet" href="<c:url value="/resources/css/devices.css" />" media="screen,projection" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" media="screen,projection" />
</head>

<body>
	<header class="header">
		<div class="container-lrg">
			<div class="col-12 spread">
				<div>
					<a class="logo"> Blood Donation Online System </a>
				</div>
				<div>
					<a class="nav-link" href="<c:url value="/login" />"> Sign In </a>
				</div>
			</div>
		</div>
		<div class="container-lrg">
			<div class="centerdevices col-12">
				<img class="mask-img" src="resources/images/hero.png" />
			</div>
		</div>
		<div class="container-sml">
			<div class="col-12 text-center">
				<h2 class="paragraph">This is the place where good donors and
					real patients meet.&nbsp;The blood you donate gives someone another
					chance at life. One day that someone may be a close relative, a
					friend, a loved one—or even you.</h2>
				<div class="ctas">
					<a class="ctas-button" href="<c:url value="/login" />"> Sign Up </a>
					<a class="ctas-button-2" href="<c:url value="/login" />"> Sign In </a>
				</div>
			</div>
		</div>
	</header>
	<div class="feature6">
		<div class="container-sml flex">
			<div class="col-12">
				<h3 class="heading">No payments. No nonsense. Just plain old
					saving lives.</h3>
				<p class="paragraph">Do you feel you don’t have much to offer?
					You have the most precious resource of all: the ability to save a
					life by donating blood! Help share this invaluable gift with
					someone in need.</p>
			</div>
		</div>
	</div>
	<div class="socialproof">
		<div class="container-lrg">
			<div class="flex text-center">
				<div class="col-3">
					<h6 class="heading">223+</h6>
					<b class="paragraph"> Patients helped </b>
				</div>
				<div class="col-3">
					<h6 class="heading">1,103+</h6>
					<b class="paragraph"> Liters of blood donated </b>
				</div>
				<div class="col-3">
					<h6 class="heading">552+</h6>
					<b class="paragraph"> Registered Users </b>
				</div>
				<div class="col-3">
					<h6 class="heading">0</h6>
					<b class="paragraph"> Payments Charged </b>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container-lrg center">
			<div class="col-7">
				<h5 class="heading">To the young and healthy it’s no loss. To
					sick it’s hope of life. Donate Blood to give back life.</h5>
			</div>
			<div class="col-5">
				<div class="ctas text-right">
					<a class="ctas-button" href="<c:url value="/login" />"> Sign Up </a>
					<a class="ctas-button-2" href="<c:url value="/login" />"> Sign In </a>
				</div>
			</div>
		</div>
		<div class="container-sml footer-nav text-center">
			<div class="col-12">
				<div>
					<span> 	&copy; 2017 Restie, Reynaldo, Francis & Bruce </span>
				</div>
			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
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