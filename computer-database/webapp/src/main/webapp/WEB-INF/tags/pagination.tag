
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<ul class="pagination " >
		<c:if test="${1==page.currentPage }">
			<li class="disabled" > <a href="#">&laquo;</a></li>
		</c:if>
		
		<c:if test="${1!=page.currentPage }">
			<li><a href="Dashboard?currentPage=${page.currentPage -1}&pattern=${page.pattern}&
				orderByColumns=${ page.orderByColumns}&orderByType=${page.orderByType}">&laquo;</a>
			</li>
		</c:if>
		
		<c:forEach var="i" begin="1" end="${numberOfPage }">
			<li 
				<c:if test="${i==page.currentPage }">
								class="active"
				</c:if>
			><a href="Dashboard?currentPage=${i}&pattern=${page.pattern}&
			orderByColumns=${page.orderByColumns}&orderByType=${page.orderByType}">${i}</a></li>
		</c:forEach>
		
		<c:if test="${numberOfPage==page.currentPage }">
			<li class="disabled"> <a href="#">&raquo;</a></li>
		</c:if>
			
		<c:if test="${numberOfPage!=page.currentPage }">
			<li ><a href="Dashboard?currentPage=${page.currentPage +1}&pattern=${page.pattern}&
				orderByColumns=${page.orderByColumns}&orderByType=${page.orderByType}">&raquo;</a></li>
		</c:if>
						
			
</ul>

