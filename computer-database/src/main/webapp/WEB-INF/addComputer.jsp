<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section id="main">

	<h1 ><spring:message code="add"/></h1>
	
	<form:form action="AddComputer" method="POST" class ="form-validation form-horizontal" commandName="computerDto" >
	
	
			<div class="form-group ">
				<label for="name" class="control-label col-sm-2"><spring:message code="computer.name"/>:</label>
				<div class="col-xs-2">
					<form:input class ="form-control " path="name" />
					<span class="help-block"><spring:message code="required"/></span>
				</div>
				<form:errors path="name"/>
			</div>
	
			<div class="form-group ">
				<label for="introduced" class="control-label col-sm-2"><spring:message code="computer.introduced"/>:</label>
				<div class="col-xs-2">
					<form:input path="dateIntroduced" class="datepicker form-control" />
				</div>
				<form:errors path="dateIntroduced"/>
			</div>
			
			<div class="form-group ">
				<label for="discontinued" class="control-label col-sm-2"><spring:message code="computer.discontinued"/>:</label>
				<div class="col-xs-2">
					<form:input path="dateDiscontinued" class="datepicker form-control" />
				</div>
				<form:errors path="dateDiscontinued"/>
			</div>
			
			<div class="form-group ">
				<label for="company" class="control-label col-sm-2"><spring:message code="computer.company"/>:</label>
				<div class="col-xs-2">
					<form:select path="company" class="form-control">
						<form:option value="0">--</form:option>
						<form:options  items="${companyList}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
				<form:errors path="company"/>
			</div>
	
		
		<div class="form-group control-label col-xs-4">
			<input type="submit" value="<spring:message code="add"/>" class="btn btn-success ">
			<spring:message code="or"/>
			<a href="Dashboard" class="btn btn-danger"><spring:message code="cancel"/></a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />