<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" tagdir="http://www.springframework.org/tags/form" %>


<section id="main">

	<h1 >Add Computer</h1>
	
	<form:form action="AddComputer" method="POST" class ="form-validation form-horizontal" commandName="computerDto" >
	
	
			<customTag:ifError test="${errorName==1 }">
				<label for="name" class="control-label col-sm-2">Computer name:</label>
				<div class="col-xs-2">
					<form:input class ="form-control " type="text" path="name" value="${computerName}" required/>
					<span class="help-block">Required</span>
				</div>
			</customTag:ifError>
	
			<customTag:ifError test="${errorIntroduced==1 }">
				<label for="introduced" class="control-label col-sm-2">Introduced date:</label>
				<div class="col-xs-2">
					<form:input type="text" path="dateIntroduced" class="datepicker form-control" value="${computerIntroduced}"/>
				</div>
			</customTag:ifError>
			
			<customTag:ifError test="${errorDiscontinued==1 }">
				<label for="discontinued" class="control-label col-sm-2">Discontinued date:</label>
				<div class="col-xs-2">
					<form:input type="text" path="dateDiscontinued" class="datepicker form-control" value="${computerDiscontinued}"/>
				</div>
			</customTag:ifError>
			
			<customTag:ifError test="${errorCompanyId==1 }">
				<label for="company" class="control-label col-sm-2">Company Name:</label>
				<div class="col-xs-2">
					<form:select path="company" class="form-control">
						<form:option value="0">--</form:option>
						<c:forEach var="company" items="${companyList}" >
							<form:option 
								value="${company.id}"
								<c:if test="${company.id==companyId }">
									selected="selected"
								</c:if>
								>${company.name }
							</form:option>
						</c:forEach>
					</form:select>
				</div>
			</customTag:ifError>
	
		
		<div class="form-group control-label col-xs-4">
			<input type="submit" value="Add" class="btn btn-success ">
			or <a href="Dashboard" class="btn btn-danger">Cancel</a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />