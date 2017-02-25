<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Reports</h5>

	<div class="row">
		<div class="col s6 m4">
			<a href="<c:url value="/reports/patients" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<p class="blue-text">Print Patients</p>
					</div>
				</div>
			</a>
			
			<a href="<c:url value="/reports/donors" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<p class="blue-text">Print Donors</p>
					</div>
				</div>
			</a>
			
			<a href="<c:url value="/reports/donations" />">
				<div class="card hoverable">
					<div class="card-content lighten-4 center">
						<p class="blue-text">Print Donation Requests</p>
					</div>
				</div>
			</a>
		</div>
	</div>
</t:template>