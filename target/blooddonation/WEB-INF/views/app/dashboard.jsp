<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Welcome ${user}!</h5>

	<div class="row">
		<div class="col s4 m4">
			<a href="<c:url value="/patient/new" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<img src="<c:url value="/resources/images/male.png" />" />
						<p class="blue-text">New Patient</p>
					</div>
				</div>
			</a>
		</div>
	
		<div class="col s4 m4">
			<a href="<c:url value="/patient/list" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<img src="<c:url value="/resources/images/conference.png" />" />
						<p class="blue-text">Patient List</p>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col s4 m4">
			<a href="<c:url value="/todo" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<img src="<c:url value="/resources/images/system.png" />" />
						<p class="blue-text">Transactions</p>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col s4 m4">
			<a href="<c:url value="/todo" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<img src="<c:url value="/resources/images/reports.png" />" />
						<p class="blue-text">Reports</p>
					</div>
				</div>
			</a>
		</div>

		<div class="col s4 m4">
			<a href="<c:url value="/todo" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<img src="<c:url value="/resources/images/settings.png" />" />
						<p class="blue-text">Settings</p>
					</div>
				</div>
			</a>
		</div>
	</div>
</t:template>