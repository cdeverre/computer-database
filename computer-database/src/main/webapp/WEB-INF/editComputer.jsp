<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section id="main">

	<h1>Edit Computer</h1>
	
	<form:form action="EditComputer" method="POST" class ="form-horizontal form-validation" commandName="computerDto">
			<form:hidden path="id" />
			
			<customTag:ifError test="${errorName==1 }">
				<label for="name" class="control-label col-sm-2">Computer name:</label>
				<div class="col-xs-2">
					<form:input class ="form-control " path="name" />
					<span class="help-block">Required</span>
				</div>
			</customTag:ifError>
	
			<customTag:ifError test="${errorIntroduced==1 }">
				<label for="introduced" class="control-label col-sm-2">Introduced date:</label>
				<div class="col-xs-2">
					<form:input path="dateIntroduced" class="datepicker form-control" />
				</div>
			</customTag:ifError>
			
			<customTag:ifError test="${errorDiscontinued==1 }">
				<label for="discontinued" class="control-label col-sm-2">Discontinued date:</label>
				<div class="col-xs-2">
					<form:input path="dateDiscontinued" class="datepicker form-control" />
				</div>
			</customTag:ifError>
			
			<customTag:ifError test="${errorCompanyId==1 }">
				<label for="company" class="control-label col-sm-2">Company Name:</label>
				<div class="col-xs-2">
					<form:select path="company" class="form-control">
						<form:option value="0">--</form:option>
						<form:options  items="${companyList}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
			</customTag:ifError>
			
		<div class="form-group control-label col-xs-4">
			<input type="submit" value="Edit" class="btn btn-success">
			or <a href="Dashboard" class="btn btn-danger">Cancel</a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />