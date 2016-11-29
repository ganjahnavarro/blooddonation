<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<h5 class="page-header">Donation Request</h5>
	
	<div class="row">
		<form:form method="POST" modelAttribute="donation" class="col s12">
			<form:input type="hidden" path="id" id="id" />
		
			<div class="row">
				<div class="input-field col m4 offset-m2">
					<i class="material-icons prefix">assignment_ind</i>
					<form:input id="title" path="title" type="text" cssClass="validate" required="required" />
					<label for="title">Title</label>
				</div>
				
				<div class="input-field col m4 offset-m2">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="expiryDate" path="expiryDate" type="date" cssClass="validate datepicker" />
					<label for="expiryDate">Expiry Date</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m8 offset-m2">
					<i class="material-icons prefix">label_outline</i>
					<form:textarea id="description" path="description" type="text" cssClass="validate" />
					<label for="description">Description</label>
				</div>
			</div>
			
			<div class="form-actions">
				<button class="btn waves-effect waves-light" type="submit" name="action">Submit
					<i class="material-icons right">send</i>
				</button>

				<a class="waves-effect btn-flat" onclick="window.history.back();">Cancel</a>
			</div>
		</form:form>
	</div>
</t:template>