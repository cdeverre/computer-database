<jsp:include page="include/header.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="main">
	<h1 id="homeTitle">${numberOfComputer} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value="" placeholder="Search name">
			<input id="searchsubmit" value="Filter by name" class="btn btn-primary" type="submit">
		</form>
		<a class="btn btn-success" id="add" href="AddComputer">Add Computer</a>
	</div>

	<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>Delete</th>
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
</section>

<jsp:include page="include/footer.jsp" />
