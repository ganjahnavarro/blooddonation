<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<div class="row">
		<div class="col s8 offset-s2">
			<h5 class="page-header">Donation Request</h5>
			
			<form:form method="POST" modelAttribute="donation"
				action="${pageContext.request.contextPath}${actionParam}${_csrf.parameterName}=${_csrf.token}"
				enctype="multipart/form-data">
			
				<form:input id="donationId" type="hidden" path="id" />
			
				<div class="row">
					<div class="input-field col m6">
						<i class="material-icons prefix">assignment_ind</i>
						<form:input id="title" path="title" type="text" cssClass="validate" required="required" />
						<label for="title">Title</label>
					</div>
					
					<div class="input-field col m6">
						<i class="material-icons prefix">label_outline</i>
						<fmt:formatDate var="formattedExpiryDate" pattern="dd MMMM, yyyy" value="${donation.expiryDate}" />
						<form:input id="expiryDate" path="expiryDate" type="date" cssClass="validate datepicker" required="required" value="${formattedExpiryDate}" />
						<label for="expiryDate">Expiry Date</label>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col m12">
						<i class="material-icons prefix">label_outline</i>
						<form:textarea id="description" path="description" type="text" cssClass="validate materialize-textarea" />
						<label for="description">Description</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col m6">
						<i class="material-icons prefix">note_add</i>
						<input id="image" type="file" name="fileUpload" size="50" value="${donation.imageFileName}" />
					</div>
				</div>

				<div class="form-actions row">
					<div class="col m12">
						<button class="btn waves-effect waves-light" type="submit" name="action">Submit
							<i class="material-icons right">send</i>
						</button>
		
						<a class="waves-effect btn-flat" onclick="window.history.back();">Cancel</a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</t:template>