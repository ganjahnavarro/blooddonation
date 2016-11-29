<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<h5 class="page-header">Register</h5>
	
	<div class="row">
		<form:form method="POST" modelAttribute="patient" class="col s12">
			<form:input type="hidden" path="id" id="id" />
		
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">assignment_ind</i>
					<form:input id="firstName" path="user.firstName" type="text" cssClass="validate" required="required" />
					<label for="firstName">First Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="middleName" path="user.middleName" type="text" cssClass="validate" />
					<label for="middleName">Middle Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="lastName" path="user.lastName" type="text" cssClass="validate" />
					<label for="lastname">Last Name</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select id="gender" path="user.gender" items="${genders}"
						itemLabel="displayString" />
					<label for="gender">Gender</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select id="bloodType" path="bloodType" items="${bloodTypes}" />
					<label for="bloodType">Blood Type</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">email</i>
					<form:input id="email" path="user.email" type="text" cssClass="validate" required="required" />
					<label for="email" data-error="Invalid email.">Email</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m8">
					<i class="material-icons prefix">language</i>
					<form:input id="address" path="user.address" type="text" cssClass="validate" required="required" />
					<label for="address">Address</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">perm_phone_msg</i>
					<form:input id="contactNo" path="user.contactNo" type="text" cssClass="validate" required="required" />
					<label for="contactNo">Contact No.</label>
				</div>
			</div>
			
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
		</form:form>
	</div>
</t:template>