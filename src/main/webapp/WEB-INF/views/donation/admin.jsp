<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ADMIN')">
	<div class="col s12">
		<ul class="tabs">
			<li class="tab"><a href="#pending">Pending</a></li>
			<li class="tab"><a href="#approved">Approved</a></li>
			<li class="tab"><a href="#disapproved">Disapproved</a></li>
			<li class="tab"><a href="#cancelled">Cancelled</a></li>
			<li class="tab"><a href="#processed">Processed</a></li>
		</ul>
		<br/>
	</div>

	<div id="pending" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th width="200"></th>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${pendingDonations}" var="donation">
					<tr>
						<td>
							<a href="<c:url value="/donation/${donation.id}" />">
								<button type="button" class="light-blue waves-effect waves-light btn btn-icon">
									<i class="material-icons">edit</i>
								</button>
							</a>
							
							<button onclick="openConfirmation('Are you sure you want to approve this request?', '<c:url value='/donation/${donation.id}/approved' />')"
								class="light-blue waves-effect waves-light btn btn-icon">
								<i class="material-icons">check</i>
							</button>
							
							<button onclick="openConfirmation('Are you sure you want to disapprove this request?', '<c:url value='/donation/${donation.id}/disapproved' />')"
								class="grey waves-effect waves-light btn btn-icon">
								<i class="material-icons">close</i>
							</button>
						</td>
						
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description} ${donation.imageFileName}</td>
						
						<td>
							<a href="<c:url value="/donor/view/${donation.donor.id}" />">
								${donation.donor.displayString}
							</a>
						</td>
						
						<td>
							<a href="<c:url value="/patient/view/${donation.patient.id}" />">
								${donation.patient.displayString}
							</a>
						</td>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="approved" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${approvedDonations}" var="donation">
					<tr>
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description} ${donation.imageFileName}</td>
						
						<td>
							<a href="<c:url value="/donor/view/${donation.donor.id}" />">
								${donation.donor.displayString}
							</a>
						</td>
						
						<td>
							<a href="<c:url value="/patient/view/${donation.patient.id}" />">
								${donation.patient.displayString}
							</a>
						</td>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="disapproved" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${disapprovedDonations}" var="donation">
					<tr>
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description} ${donation.imageFileName}</td>
						
						<td>
							<a href="<c:url value="/donor/view/${donation.donor.id}" />">
								${donation.donor.displayString}
							</a>
						</td>
						
						<td>
							<a href="<c:url value="/patient/view/${donation.patient.id}" />">
								${donation.patient.displayString}
							</a>
						</td>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="cancelled" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${cancelledDonations}" var="donation">
					<tr>
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description} ${donation.imageFileName}</td>
						
						<td>
							<a href="<c:url value="/donor/view/${donation.donor.id}" />">
								${donation.donor.displayString}
							</a>
						</td>
						
						<td>
							<a href="<c:url value="/patient/view/${donation.patient.id}" />">
								${donation.patient.displayString}
							</a>
						</td>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="processed" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${processedDonations}" var="donation">
					<tr>
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description} ${donation.imageFileName}</td>
						
						<td>
							<a href="<c:url value="/donor/view/${donation.donor.id}" />">
								${donation.donor.displayString}
							</a>
						</td>
						
						<td>
							<a href="<c:url value="/patient/view/${donation.patient.id}" />">
								${donation.patient.displayString}
							</a>
						</td>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</sec:authorize>