<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags/" %>

<section id="main">
	<h1 id="homeTitle">${numberOfComputer} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="pattern" value="${pattern}" placeholder="Search name">
			<input id="searchsubmit" value="Filter by name" class="btn btn-primary" type="submit">
		</form>
		<a class="btn btn-success" id="add" href="AddComputer">Add Computer</a>
	</div>

	<div class="row">
	<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th class="col-md-5">Computer Name  
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=name&orderByType=ASC">
							<span class="glyphicon glyphicon-chevron-up"></span>
						</a>
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=name&orderByType=DESC">
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
					</th class="col-md-2">
					<th>Introduced Date  
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=introduced&orderByType=ASC">
							<span class="glyphicon glyphicon-chevron-up"></span>
						</a>
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=introduced&orderByType=DESC">
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
					</th>
					<!-- Table header for Discontinued Date -->
					<th class="col-md-2">Discontinued Date  
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=discontinued&orderByType=ASC">
							<span class="glyphicon glyphicon-chevron-up"></span>
						</a>
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=discontinued&orderByType=DESC">
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
					</th>
					<!-- Table header for Company -->
					<th class="col-md-2">Company  
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=company_id&orderByType=ASC">
							<span class="glyphicon glyphicon-chevron-up"></span>
						</a>
						<a href="Dashboard?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=company_id&orderByType=DESC">
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
					</th>
					<th class="col-md-1">Delete</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="computer" items="${computerList}" >
					<tr>
						<td><a href="EditComputer?id=${computer.id }" onclick="">${computer.name}</a></td>
						<td><fmt:formatDate type="date" value="${computer.dateIntroduced.getTime()}" /></td>
						<td><fmt:formatDate type="date" value="${computer.dateDiscontinued.getTime()}" /></td>
						<td>${computer.company.name}</td>
						<td><a class="btn btn-danger" id="delete" href="DeleteComputer?id=${computer.id }">Delete</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		</div>
		
		<customTag:pagination currentPage ="${currentPage}" numberOfPage="${numberOfPage}" pattern="${pattern }"> </customTag:pagination>
		
</section>

<jsp:include page="include/footer.jsp" />
