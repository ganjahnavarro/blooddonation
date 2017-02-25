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
					<th width="140"></th>
					<th data-field="bloodtype">Blood Type</th>
					<th data-field="name">Name</th>
					<th data-field="address">Address</th>
					<th data-field="contact">Contact No.</th>
					<th data-field="email">Email</th>
					<th data-field="email">Active</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${patients}" var="patient">
					<tr>
						<td>
							<a href="<c:url value="/patient/${patient.id}" />">
								<button type="button" class="light-blue waves-effect waves-light btn btn-icon">
									<i class="material-icons">edit</i>
								</button>
							</a>
						
							<button onclick="openConfirmation('Are you sure you want to change active status of this patient?', '<c:url value='/patient/${patient.id}/status' />')"
								class="grey waves-effect waves-light btn btn-icon">
								<i class="material-icons">power_settings_new</i>
							</button>
						</td>
					
						<td>${patient.bloodType}</td>
						<td>${patient.displayString}</td>
						<td>${patient.user.address}</td>
						<td>${patient.user.contactNo}</td>
						<td>${patient.user.email}</td>
						<td>${patient.user.active}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:template>