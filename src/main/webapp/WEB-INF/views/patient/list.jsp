<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Patients</h5>
	
	<div class="row">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="name">Name</th>
					<th data-field="name">Blood Type</th>
					<th data-field="address">Address</th>
					<th data-field="contact">Contact No.</th>
					<th data-field="email">Email</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${patients}" var="patient">
					<tr>
						<td>${patient.bloodType}</td>
						<td>${patient.displayString}</td>
						<td>${patient.address}</td>
						<td>${patient.contactNo}</td>
						<td>${patient.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:template>