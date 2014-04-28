<jsp:include page="include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="date.pattern" var="date_pattern"/>

<section id="main">
	<h1 id="homeTitle">${numberOfComputer} <spring:message code="computers_found"/> </h1>
	<div id="actions">
		<form action="Dashboard" method="GET">
			<input type="search" id="searchbox" name="pattern" value="${page.pattern}" 
				placeholder="<spring:message code="search.text"/>">
			<input id="searchsubmit" value="<spring:message code="search.submit"/>" class="btn btn-primary" type="submit">
		</form>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<customTag:link path="AddComputer" preference='class="btn btn-success" id="add"'> 
				<span class="glyphicon glyphicon-plus"></span>
				<spring:message code="add"/>
			</customTag:link>
		</sec:authorize>
	</div>

	<c:if test="${add==true}">
		<div class="alert alert-success alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" >&times;</button>
			<spring:message code="success.add"/>
		</div>
	</c:if>
	
	<c:if test="${update==true}">
		<div class="alert alert-success alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" >&times;</button>
			<spring:message code="success.update"/>
		</div>
	</c:if>
	
	<c:if test="${delete==true }">
		<div class="alert alert-success alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" >&times;</button>
			<spring:message code="success.delete"/>
		</div>
	</c:if>
	
	
	<div class="row">
	<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th class="col-md-5"><spring:message code="computer.name"/> 
						<customTag:link path="Dashboard" orderByColumns="name" orderByType="true"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-up"></span>
						</customTag:link>
							 
						<customTag:link path="Dashboard" orderByColumns="name" orderByType="false"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-down"></span>
						</customTag:link>
						
					</th >
					<th class="col-md-2"><spring:message code="computer.introduced"/>  
						<customTag:link path="Dashboard" orderByColumns="introduced" orderByType="true"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-up"></span>
						</customTag:link>
							 
						<customTag:link path="Dashboard" orderByColumns="introduced" orderByType="false"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-down"></span>
						</customTag:link>
					</th>
					<!-- Table header for Discontinued Date -->
					<th class="col-md-2"> <spring:message code="computer.discontinued"/> 
						<customTag:link path="Dashboard" orderByColumns="discontinued" orderByType="true"
							currentPage="${page.currentPage}" pattern="${page.pattern}" >
							<span class="glyphicon glyphicon-chevron-up"></span>
						</customTag:link>
							 
						<customTag:link path="Dashboard" orderByColumns="discontinued" orderByType="false"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-down"></span>
						</customTag:link>
					</th>
					<!-- Table header for Company -->
					<th class="col-md-2"><spring:message code="computer.company"/>  
						<customTag:link path="Dashboard" orderByColumns="companyName" orderByType="true"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-up"></span>
						</customTag:link>
							 
						<customTag:link path="Dashboard" orderByColumns="companyName" orderByType="false"
							 currentPage="${page.currentPage}" pattern="${page.pattern}">
							 <span class="glyphicon glyphicon-chevron-down"></span>
						</customTag:link>
					</th>
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<th class="col-md-1"><spring:message code="computer.delete"/></th>
					</sec:authorize>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="computer" items="${computerList}" >
					<tr>
						<td>
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
								<customTag:link path="EditComputer" otherParameters="id=${computer.id }" 
								currentPage="${page.currentPage}" pattern="${page.pattern} "
								orderByColumns="${page.orderByColumns}" orderByType="${page.orderByType }">
									${computer.name }
								</customTag:link>
							</sec:authorize>
							<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
								${computer.name }
							</sec:authorize>
						</td>
					
						<td>
							<joda:format value="${computer.introduced}" 
							pattern="${date_pattern }"/> 
						</td>
						<td>
							<joda:format value="${computer.discontinued}" 
							pattern="${date_pattern }"/>
						</td>
						<td>${computer.company.name}</td>
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<td>
								<customTag:link path="DeleteComputer" otherParameters="id=${computer.id }" 
								preference='class="btn btn-danger" id="delete"'
								currentPage="${page.currentPage}" pattern="${page.pattern}"
								orderByColumns="${page.orderByColumns}" orderByType="${page.orderByType }">
									<span class="glyphicon glyphicon-trash"></span> 
									<spring:message code="computer.delete"/>
								</customTag:link>
							</td>
						</sec:authorize>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		</div>
		
		<customTag:pagination> </customTag:pagination>
		
</section>

<jsp:include page="include/footer.jsp" />
