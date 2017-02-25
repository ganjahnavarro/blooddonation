<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Blood Donation Online System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="<c:url value="/resources/css/materialize.css" />" media="screen,projection"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />" media="screen,projection"/>
</head>

<body>
	<div class="parallax-container">
		<nav class="transparent" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo">Blood Donation Online System</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a href="<c:url value="/login" />">Sign in</a>
					</li>
				</ul>

				<ul id="nav-mobile" class="side-nav">
					<li>
						<a href="<c:url value="/login" />">Sign in</a>
					</li>
				</ul>
				
				<a href="#" data-activates="nav-mobile" class="button-collapse">
					<i class="material-icons">menu</i>
				</a>
			</div>
		</nav>

		<div class="section no-pad-bot">
			<div class="container">
				<br>
				<br>
				<h1 class="header center">Welcome</h1>
				<div class="row center">
					<h5 class="header col s12 light">Sed egestas arcu vel magna facilisis, nec pretium arcu molestie.</h5>
				</div>
				<div class="row center">
					<a href="<c:url value="/register" />" class="btn-large waves-effect lighten-1">Sign up</a>
				</div>
				<br>
				<br>

			</div>
		</div>
		<div class="parallax">
			<img src="resources/images/background.jpg"
				alt="Unsplashed background img 1">
		</div>
	</div>


	<div class="container">
		<div class="section">

			<!--	 Icon Section	 -->
			<div class="row">
				<div class="col s12 m4">
					<div class="icon-block">
						<div class="center">
							<img class="feature-image" src="<c:url value="/resources/images/home_microscope.png" />" />
						</div>
						
						<h5 class="center">Real time</h5>

						<p class="light">Mauris varius orci ac quam iaculis commodo. 
							Sed tempus mauris vitae erat lacinia, id varius ante sagittis. 
							Aliquam ut metus at ex tincidunt commodo sit amet ac quam</p>
					</div>
				</div>

				<div class="col s12 m4">
					<div class="icon-block">
						<div class="center">
							<img class="feature-image" src="<c:url value="/resources/images/home_book.png" />" />
						</div>
						
						<h5 class="center">Hassle-free</h5>

						<p class="light">Vestibulum vehicula nunc a ligula pretium, 
							ut scelerisque lectus tincidunt. Donec in aliquam risus. 
							Duis blandit vulputate nulla non ultricies. Curabitur a venenatis est.</p>
					</div>
				</div>

				<div class="col s12 m4">
					<div class="icon-block">
						<div class="center">
							<img class="feature-image" src="<c:url value="/resources/images/home_molecule.png" />" />
						</div>
						
						<h5 class="center">Bla bla bla</h5>

						<p class="light">Phasellus vestibulum egestas nisi vitae ornare.
							Suspendisse potenti. Suspendisse facilisis leo sit amet pulvinar consequat.
						 	Etiam placerat porttitor mi, sit amet pharetra lorem mattis sed.</p>
					</div>
				</div>
			</div>

		</div>
	</div>


	<div class="parallax-container valign-wrapper">
		<div class="section no-pad-bot">
			<div class="container">
				<div class="row center">
					<h5 class="header col s12 light">Maecenas vitae tellus a nisl vulputate dictum. Aliquam bibendum augue libero</h5>
				</div>
			</div>
		</div>
		<div class="parallax">
			<img src="resources/images/background2.jpg"
				alt="Unsplashed background img 2">
		</div>
	</div>

	<div class="container">
		<div class="section">

			<div class="row">
				<div class="col s12 center">
					<h3>
						<i class="mdi-content-send"></i>
					</h3>
					<h4>Contact Us</h4>
					<p class="left-align light">Lorem ipsum dolor sit amet,
						consectetur adipiscing elit. Nullam scelerisque id nunc nec
						volutpat. Etiam pellentesque tristique arcu, non consequat magna
						fermentum ac. Cras ut ultricies eros. Maecenas eros justo,
						ullamcorper a sapien id, viverra ultrices eros. Morbi sem neque,
						posuere et pretium eget, bibendum sollicitudin lacus. Aliquam
						eleifend sollicitudin diam, eu mattis nisl maximus sed. Nulla
						imperdiet semper molestie. Morbi massa odio, condimentum sed ipsum
						ac, gravida ultrices erat. Nullam eget dignissim mauris, non
						tristique erat. Vestibulum ante ipsum primis in faucibus orci
						luctus et ultrices posuere cubilia Curae;</p>
				</div>
			</div>

		</div>
	</div>


	<div class="parallax-container valign-wrapper">
		<div class="section no-pad-bot">
			<div class="container">
				<div class="row center">
					<h5 class="header col s12 light">Suspendisse ultricies lobortis orci non dictum. 
						Integer mollis sollicitudin nunc, quis varius sapien ultricies vitae.</h5>
				</div>
			</div>
		</div>
		<div class="parallax">
			<img src="resources/images/background3.jpg"
				alt="Unsplashed background img 3">
		</div>
	</div>

	<footer class="page-footer">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">About Us</h5>
					<p class="grey-text text-lighten-4">Donec laoreet ornare massa pretium fringilla. 
						Vivamus nulla metus, consectetur sit amet pharetra a, egestas non mi. 
						Etiam eleifend dui neque. Donec laoreet arcu ligula, 
						eget vulputate orci fringilla id. 
						Vestibulum ultricies magna eu lorem euismod auctor at vitae lacus. 
						Mauris sapien erat, dapibus vel tellus dapibus, dapibus pulvinar nunc.</p>
				</div>
				<div class="col l3 s12">
					<h5 class="white-text">Settings</h5>
					<ul>
						<li><a class="white-text" href="#!">Link 1</a></li>
						<li><a class="white-text" href="#!">Link 2</a></li>
						<li><a class="white-text" href="#!">Link 3</a></li>
						<li><a class="white-text" href="#!">Link 4</a></li>
					</ul>
				</div>
				<div class="col l3 s12">
					<h5 class="white-text">Connect</h5>
					<ul>
						<li><a class="white-text" href="#!">Link 1</a></li>
						<li><a class="white-text" href="#!">Link 2</a></li>
						<li><a class="white-text" href="#!">Link 3</a></li>
						<li><a class="white-text" href="#!">Link 4</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
				Made by <a href="#">restie&amp;friends</a>
			</div>
		</div>
	</footer>
	
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