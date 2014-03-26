
<%@ attribute name="path" required="true" %>
<%@ attribute name="currentPage" required="true" %>
<%@ attribute name="pattern" required="true" %>
<%@ attribute name="orderByColumns" required="true" %>
<%@ attribute name="text"  %>
<%@ attribute name="preference" %>

	<a ${preference} href="${path}?currentPage=${currentPage}&pattern=${pattern}&orderByColumns=${orderByColumns}">${text}</a>