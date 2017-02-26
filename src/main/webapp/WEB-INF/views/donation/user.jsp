<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="!hasRole('ADMIN')">
	<div class="col s12">
		<ul class="tabs">
			<li class="tab"><a href="#personal">My Requests</a></li>
			
			<c:choose>
				<c:when test="${isPatient}">
					<li class="tab"><a href="#assistable">Looking for patient</a></li>
				</c:when>
				<c:otherwise>
					<li class="tab"><a href="#assistable">Looking for donor</a></li>
				</c:otherwise>
			</c:choose>
			
			<li class="tab"><a href="#processed">Processed</a></li>
		</ul>
		<br/>
	</div>

	<div id="personal" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th width="140"></th>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					<th data-field="donor">Donor</th>
					<th data-field="patient">Patient</th>
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${personalDonations}" var="donation">
					<tr>
						<td>
							<c:if test="${donation.status == 'PENDING'}">
								<a href="<c:url value="/donation/${donation.id}" />">
									<button type="button" class="light-blue waves-effect waves-light btn btn-icon">
										<i class="material-icons">edit</i>
									</button>
								</a>
								
								<button onclick="openConfirmation('Are you sure you want to cancel this request?', '<c:url value='/donation/${donation.id}/cancelled' />')"
									class="grey waves-effect waves-light btn btn-icon">
									<i class="material-icons">close</i>
								</button>
							</c:if>
						</td>
						
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description}
							<c:if test="${not empty donation.imageFileName}"> 
								<a href="<c:url value="/donation/attachment/${donation.id}/download" />">
									<span class="donations-badge badge">attachment</span>
								</a>
							</c:if>
						</td>
						<td>${donation.donor.displayString}</td>
						<td>${donation.patient.displayString}</td>
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="assistable" class="col s12">
		<table class="highlight responsive-table bordered">
			<thead>
				<tr>
					<th width="80"></th>
					<th data-field="expiryDate">Expiry Date</th>
					<th data-field="title">Title</th>
					<th data-field="description">Description</th>
					
					<c:choose>
						<c:when test="${isPatient}">
							<th data-field="description">Donor</th>
						</c:when>
						<c:otherwise>
							<th data-field="description">Patient</th>
						</c:otherwise>
					</c:choose>
					
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${assistableDonations}" var="donation">
					<tr>
						<td>
							<button onclick="openConfirmation('Are you sure you want to provide help to this request?', '<c:url value='/donation/${donation.id}/processed' />')"
								class="light-blue waves-effect waves-light btn btn-icon">
								<i class="material-icons">check</i>
							</button>
						</td>
						
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description}</td>
						
						<c:choose>
							<c:when test="${isPatient}">
								<td>${donation.donor.user.firstName}</td>
							</c:when>
							<c:otherwise>
								<td>${donation.patient.user.firstName}</td>
							</c:otherwise>
						</c:choose>
						
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
	
					<c:choose>
						<c:when test="${isPatient}">
							<th data-field="description">Donor</th>
						</c:when>
						<c:otherwise>
							<th data-field="description">Patient</th>
						</c:otherwise>
					</c:choose>
	
					<th data-field="status">Status</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${processedDonations}" var="donation">
					<tr>
						<td><fmt:formatDate pattern="MM/dd/yyyy" value="${donation.expiryDate}" /></td>
						<td>${donation.title}</td>
						<td>${donation.description}
							<c:if test="${not empty donation.imageFileName}"> 
								<a href="<c:url value="/donation/attachment/${donation.id}/download" />">
									<span class="donations-badge badge">attachment</span>
								</a>
							</c:if>
						</td>
						
						<c:choose>
							<c:when test="${isPatient}">
								<td>
									<a href="<c:url value="/donor/view/${donation.donor.id}" />">
										${donation.donor.displayString}
									</a>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<a href="<c:url value="/patient/view/${donation.patient.id}" />">
										${donation.patient.displayString}
									</a>
								</td>
							</c:otherwise>
						</c:choose>
						
						<td>${donation.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</sec:authorize>