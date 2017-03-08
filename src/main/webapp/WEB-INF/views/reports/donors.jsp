<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<div id="donors" class="col s8">
	<form:form method="POST"
		action="${pageContext.request.contextPath}/reports/donors">

		<div class="card">
			<div class="card-content">
				<p class="blue-text">Filters</p>
			
				<div class="row">
					<div class="input-field col m6">
						<i class="material-icons prefix">date_range</i>
						<input id="startDate" name="startDate" type="date" class="datepicker" />
						<label for="startDate">Start Date</label>
					</div>
					
					<div class="input-field col m6">
						<i class="material-icons prefix">date_range</i>
						<input id="endDate" name="endDate" type="date" class="datepicker" />
						<label for="endDate">End Date</label>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col m6">
						<i class="material-icons prefix">opacity</i>
						<select id="bloodType" name="bloodType">
							<option value="${null}">All</option>
							<c:forEach items="${bloodTypes}" var="bloodType">
								<option value="${bloodType}">${bloodType}</option>
							</c:forEach>
						</select>
						<label for="bloodType">Blood Type</label>
					</div>
					
					<div class="input-field col m6">
						<i class="material-icons prefix">sort</i>
						<select id="orderBy" name="orderBy">
							<c:forEach items="${usersOrderBy}" var="orderBy">
								<option value="${orderBy.key}">${orderBy.value}</option>
							</c:forEach>
						</select>
						<label for="orderBy">Order By</label>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-actions row">
			<div class="col m12">
				<button class="btn waves-effect waves-light" type="submit" name="action">Print
					<i class="material-icons right">print</i>
				</button>
			</div>
		</div>
	</form:form>
</div>