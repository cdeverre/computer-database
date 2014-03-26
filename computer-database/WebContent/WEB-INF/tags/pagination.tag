
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ attribute name="numberOfPage" required="true" %>
<%@ attribute name="currentPage" required="true" %>
<%@ attribute name="pattern" required="true" %>


<ul class="pagination " >
		<li 
			<c:if test="${1==currentPage }">
								class="disabled"
			</c:if>
		>
			<a href="Dashboard?currentPage=${currentPage -1}&pattern=${pattern}&
			orderByColumns=${ orderByColumns}&orderByType=${orderByType}">&laquo;</a>
		</li>
		<c:forEach var="i" begin="1" end="${numberOfPage }">
			<li 
				<c:if test="${i==currentPage }">
								class="active"
				</c:if>
			><a href="Dashboard?currentPage=${i}&pattern=${pattern}&
			orderByColumns=${orderByColumns}&orderByType=${orderByType}">${i}</a></li>
		</c:forEach>
		<li
			<c:if test="${numberOfPage==currentPage }">
								class="disabled"
			</c:if>
		><a href="Dashboard?currentPage=${currentPage +1}&pattern=${pattern}&
			orderByColumns=${orderByColumns}&orderByType=${orderByType}">&raquo;</a></li>
</ul>

