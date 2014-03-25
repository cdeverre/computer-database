<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<section id="main">

	<h1>Edit Computer</h1>
	
	<form action="EditComputer" method="POST" class ="form-horizontal">
		<fieldset>
			<input name="id" type="HIDDEN" value="${id}" />
		
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">Computer name:</label>
				<div class="col-xs-2">
					<input class ="form-control " type="text" name="name" value="${computerName}" required/>
					<span class="help-block">Required</span>
				</div>
			</div>
	
			<div class="form-group">
				<label for="introduced" class="control-label col-sm-2">Introduced date:</label>
				<div class="col-xs-2">
					<input type="text" name="introduced" class="datepicker form-control" value="${computerIntroduced}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="discontinued" class="control-label col-sm-2">Discontinued date:</label>
				<div class="col-xs-2">
					<input type="text" name="discontinued" class="datepicker form-control" value="${computerDiscontinued}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="company" class="control-label col-sm-2">Company Name:</label>
				<div class="col-xs-2">
					<select name="company" class="form-control">
						<option value="null">--</option>
						<c:forEach var="company" items="${companyList}" >
							<option 
								value="${company.id}"
								<c:if test="${company.id==companyId }">
									selected="selected"
								</c:if>
								>${company.name }
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="form-group control-label col-xs-4">
			<input type="submit" value="Edit" class="btn btn-success">
			or <a href="Dashboard" class="btn btn-danger">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />