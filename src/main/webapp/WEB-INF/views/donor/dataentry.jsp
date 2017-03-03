<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<h5 class="page-header">Donor</h5>
	
	<div class="row">
		<form:form method="POST" modelAttribute="donor"
			action="${pageContext.request.contextPath}${actionParam}${_csrf.parameterName}=${_csrf.token}"
			cssClass="col s12">
		
			<form:input id="donorId" type="hidden" path="id" />
			<form:input id="userId" type="hidden" path="user.id" />
		
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">assignment_ind</i>
					<form:input id="firstName" path="user.firstName" type="text" cssClass="validate" required="required" disabled="${disableEdit}" />
					<label for="firstName">First Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="middleName" path="user.middleName" type="text" cssClass="validate" disabled="${disableEdit}" />
					<label for="middleName">Middle Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="lastName" path="user.lastName" type="text" cssClass="validate" disabled="${disableEdit}" />
					<label for="lastname">Last Name</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select id="gender" path="user.gender" items="${genders}"
						itemLabel="displayString" disabled="${disableEdit}" />
					<label for="gender">Gender</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select id="bloodType" path="bloodType" items="${bloodTypes}" disabled="${disableEdit}" />
					<label for="bloodType">Blood Type</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">email</i>
					<form:input id="email" path="user.email" type="text" cssClass="validate" required="required" disabled="${disableEdit}" />
					<label for="email" data-error="Invalid email.">Email</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m8">
					<i class="material-icons prefix">language</i>
					<form:input id="address" path="user.address" type="text" cssClass="validate" required="required" disabled="${disableEdit}" />
					<label for="address">Address</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">perm_phone_msg</i>
					<form:input id="contactNo" path="user.contactNo" type="number"
						cssClass="validate" required="required" disabled="${disableEdit}" />
					<label for="contactNo">Contact No.</label>
				</div>
			</div>
			
			<c:if test="${!disableEdit}">
				<div class="row">
					<div class="input-field col m4">
						<i class="material-icons prefix">assignment_ind</i>
						<form:input id="userName" path="user.userName" type="text" cssClass="validate" required="required" />
						<label for="userName">Username</label>
					</div>
					
					<div class="input-field col m4">
						<i class="material-icons prefix">lock_open</i>
						<form:input id="password" path="user.password" type="password" cssClass="validate" required="required" />
						<label for="password">Password</label>
					</div>
					
					<div class="input-field col m4">
						<i class="material-icons prefix">lock_outline</i>
						<input id="passwordConfirmation" name="passwordConfirmation" type="password" class="validate" required="required">
						<label for="passwordConfirmation">Re-Enter Password</label>
					</div>
				</div>
				
				<div class="row">
					<p class="referralMessage">Since any friend of yours is a friend of ours, you can share this site on your friends and family.</p>
					<div class="input-field col m4">
						<i class="material-icons prefix">email</i>
						<input id="referrals" name="referrals" type="text" >
						<label for="referrals">Referred by (Email)</label>
					</div>
				</div>
	
				<p class="eligibility-checkbox">
					<input id="healthy" type="checkbox" class="validate" required="required" />
					<label for="healthy">I have good general health and feeling well</label>
				</p>
				
				<p class="eligibility-checkbox">
					<input id="non-malnourish" type="checkbox" class="validate" required="required"/>
					<label for="non-malnourish">My weight is above 110 lbs</label>
				</p>
	
				<div class="form-actions">
					<button class="btn waves-effect waves-light" type="submit" name="action">Submit
						<i class="material-icons right">send</i>
					</button>
	
					<a class="waves-effect btn-flat" onclick="window.history.back();">Cancel</a>
				</div>
			</c:if>
		</form:form>
	</div>
</t:template>