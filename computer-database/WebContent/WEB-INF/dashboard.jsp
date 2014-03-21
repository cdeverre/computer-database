<jsp:include page="include/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<section id="main">
	<h1 id="homeTitle">${fn:length(computerList)} Computers found</h1>
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
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="computer" items="${computerList}" >
					<tr>
						<td><a href="#" onclick="">${computer.name}</a></td>
						<td>${computer.dateIntroduced}</td>
						<td>${computer.dateDiscontinued}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
</section>

<jsp:include page="include/footer.jsp" />
