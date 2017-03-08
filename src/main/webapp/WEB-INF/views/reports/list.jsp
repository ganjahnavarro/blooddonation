<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Reports</h5>

	<div class="row">
		<div class="col s12">
			<ul class="tabs">
				<li class="tab"><a href="#patients">Patients</a></li>
				<li class="tab"><a href="#donors">Donors</a></li>
				<li class="tab"><a href="#donations">Donation Requests</a></li>
			</ul>
			<br/>
		</div>
		
		<jsp:include page="patients.jsp" />
		<jsp:include page="donors.jsp" />
		<jsp:include page="donations.jsp" />
	</div>
</t:template>