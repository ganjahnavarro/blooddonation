<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h5 class="page-header">Donation Requests</h5>
	
	<div class="row">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${donations}" var="donation">
					<tr>
						<td>${donation.expiryDate}</td>
						<td>${donation.title}</td>
						<td>${donation.description}</td>
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:template>