<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>

<section id="main">
	<h1 id="homeTitle">${numberOfComputer} Computers found</h1>
	<div id="actions">
		<form action="Dashboard" method="GET">
			<input type="search" id="searchbox" name="pattern" value="${pattern}" placeholder="Search name">
			<input id="searchsubmit" value="Filter by name" class="btn btn-primary" type="submit">
		</form>
		<customTag:link path="AddComputer" text='<span class="glyphicon glyphicon-plus"></span> AddComputer' preference='class="btn btn-success" id="add"'></customTag:link>
	</div>

	<div class="row">
	<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th class="col-md-5">Computer Name  
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-up"></span>'
							 orderByColumns="name" orderByType="true"></customTag:link>
							 
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-down"></span>'
							 orderByColumns="name" orderByType="false"></customTag:link>
						
					</th >
					<th class="col-md-2">Introduced Date  
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-up"></span>'
							 orderByColumns="introduced" orderByType="true"></customTag:link>
							 
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-down"></span>'
							 orderByColumns="introduced" orderByType="false"></customTag:link>
					</th>
					<!-- Table header for Discontinued Date -->
					<th class="col-md-2">Discontinued Date  
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-up"></span>'
							 orderByColumns="discontinued" orderByType="true"></customTag:link>
							 
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-down"></span>'
							 orderByColumns="discontinued" orderByType="false"></customTag:link>
					</th>
					<!-- Table header for Company -->
					<th class="col-md-2">Company  
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-up"></span>'
							 orderByColumns="company_id" orderByType="true"></customTag:link>
							 
						<customTag:link path="Dashboard" text='<span class="glyphicon glyphicon-chevron-down"></span>'
							 orderByColumns="company_id" orderByType="false"></customTag:link>
					</th>
					<th class="col-md-1">Delete</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="computer" items="${computerList}" >
					<tr>
						<td><customTag:link path="EditComputer" otherParameters="id=${computer.id }" text="${computer.name }">
						</customTag:link></td>
						<td><fmt:formatDate type="date" value="${computer.dateIntroduced.getTime()}" /></td>
						<td><fmt:formatDate type="date" value="${computer.dateDiscontinued.getTime()}" /></td>
						<td>${computer.company.name}</td>
						<td><customTag:link path="DeleteComputer" otherParameters="id=${computer.id }" 
						text='<span class="glyphicon glyphicon-trash"></span> Delete'
						preference='class="btn btn-danger" id="delete"'></customTag:link></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		</div>
		
		<customTag:pagination> </customTag:pagination>
		
</section>

<jsp:include page="include/footer.jsp" />
