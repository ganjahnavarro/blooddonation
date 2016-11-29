<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Donors</h5>
	
	<div class="row">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="bloodtype">Blood Type</th>
					<th data-field="name">Name</th>
					<th data-field="address">Address</th>
					<th data-field="contact">Contact No.</th>
					<th data-field="email">Email</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${donors}" var="donor">
					<tr>
						<td>${donor.bloodType}</td>
						<td>${donor.displayString}</td>
						<td>${donor.user.address}</td>
						<td>${donor.user.contactNo}</td>
						<td>${donor.user.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:template>