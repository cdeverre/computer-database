<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ attribute name="test" required="true" %>

<c:choose>
	<c:when test="${test }">
		<div class="form-group has-error">
			<jsp:doBody/>
		</div>
	</c:when>
	<c:otherwise>
		<div class="form-group ">
			<jsp:doBody/>
		</div>
	</c:otherwise>
</c:choose>