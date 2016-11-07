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
					<form:input id="firstName" path="firstName" type="text" cssClass="validate" required="required" />
					<label for="firstName">First Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="middleName" path="middleName" type="text" class="validate" />
					<label for="middleName">Middle Name</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:input id="lastName" path="lastName" type="text" class="validate" />
					<label for="lastname">Last Name</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select path="gender" id="gender" items="${genders}"
						itemLabel="displayString" />
					<label for="gender">Gender</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">label_outline</i>
					<form:select path="bloodType" id="bloodType" items="${bloodTypes}" />
					<label for="bloodType">Blood Type</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m8">
					<i class="material-icons prefix">language</i>
					<form:input id="address" path="address" type="text" class="validate" />
					<label for="address">Address</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">perm_phone_msg</i>
					<form:input id="contactNo" path="contactNo" type="text" class="validate" />
					<label for="contactNo">Contact No.</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col m4">
					<i class="material-icons prefix">email</i>
					<form:input id="email" path="email" type="email" class="validate" required="required" />
					<label for="email" data-error="Invalid email.">Email</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">lock_open</i>
					<input id="password" name="firstname" type="password" class="validate" required="required">
					<label for="password">Password</label>
				</div>
				
				<div class="input-field col m4">
					<i class="material-icons prefix">lock_outline</i>
					<input id="passwordconfirmation" name="firstname" type="password" class="validate" required="required">
					<label for="passwordconfirmation">Re-Enter Password</label>
				</div>
			</div>

			<p class="eligibility-checkbox">
				<input type="checkbox" id="healthy" class="validate" required="required" />
				<label for="healthy">I have good general health and feeling well</label>
			</p>
			
			<p class="eligibility-checkbox">
				<input type="checkbox" id="non-malnourish" class="validate" required="required"/>
				<label for="non-malnourish">My weight is above 110 lbs</label>
			</p>

			<div class="form-actions">
				<button class="btn waves-effect waves-light" type="submit" name="action">Submit
					<i class="material-icons right">send</i>
				</button>

				<a class="waves-effect btn-flat">Cancel</a>
			</div>
		</form:form>
	</div>
</t:template>