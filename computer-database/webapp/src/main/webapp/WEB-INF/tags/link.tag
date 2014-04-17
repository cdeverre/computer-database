<%@ attribute name="path" required="true" %>
<%@ attribute name="preference" %>
<%@ attribute name="currentPage" %>
<%@ attribute name="pattern" %>
<%@ attribute name="orderByColumns" %>
<%@ attribute name="orderByType" %>
<%@ attribute name="otherParameters" %>



	<a ${preference} href="${path}?currentPage=${currentPage}&pattern=${pattern}&
			orderByColumns=${orderByColumns}&orderByType=${orderByType}&${otherParameters}"><jsp:doBody/></a>
			