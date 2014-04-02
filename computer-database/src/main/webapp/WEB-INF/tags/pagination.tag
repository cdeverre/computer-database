
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<ul class="pagination " >
		<c:if test="${1==currentPage }">
			<li class="disabled" > <a href="#">&laquo;</a></li>
		</c:if>
		
		<c:if test="${1!=currentPage }">
			<li><a href="Dashboard?currentPage=${currentPage -1}&pattern=${pattern}&
				orderByColumns=${ orderByColumns}&orderByType=${orderByType}">&laquo;</a>
			</li>
		</c:if>
		
		<c:forEach var="i" begin="1" end="${numberOfPage }">
			<li 
				<c:if test="${i==currentPage }">
								class="active"
				</c:if>
			><a href="Dashboard?currentPage=${i}&pattern=${pattern}&
			orderByColumns=${orderByColumns}&orderByType=${orderByType}">${i}</a></li>
		</c:forEach>
		
		<c:if test="${numberOfPage==currentPage }">
			<li class="disabled"> <a href="#">&raquo;</a></li>
		</c:if>
			
		<c:if test="${numberOfPage!=currentPage }">
			<li ><a href="Dashboard?currentPage=${currentPage +1}&pattern=${pattern}&
				orderByColumns=${orderByColumns}&orderByType=${orderByType}">&raquo;</a></li>
		</c:if>
						
			
</ul>

