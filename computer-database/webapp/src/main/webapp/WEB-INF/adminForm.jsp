<jsp:include page="/WEB-INF/include/header.jsp" />

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  


 <section id="main">
	<div id="login-box">
 
		<h3><spring:message code="adminForm.title"/></h3>
 
		 
		<form name='loginForm' action="j_spring_security_check" method='POST' class ="form-validation form-horizontal">
 
 		<div class="form-group ">
			<label for="username" class="control-label col-sm-2"><spring:message code="adminForm.user"/>:</label>
			<div class="col-xs-2">
				<input type='text' name='username' value=''>
			</div>
		</div>
		
		<div class="form-group ">
			<label for="password" class="control-label col-sm-2"><spring:message code="adminForm.password"/>:</label>
			<div class="col-xs-2">
				<input type='password' name='password' value=''>
			</div>
		</div>
		<div class="form-group ">
			<c:if test="${not empty error}">
				<div class="error"><spring:message code="adminForm.error"/></div>
			</c:if>
		</div>
			
		<div class="form-group control-label col-xs-4">
			<input type="submit" value="<spring:message code="adminForm.connection"/>" class="btn btn-success ">
			<a href="Dashboard" class="btn btn-danger"><spring:message code="cancel"/></a>
		</div>
  
		</form>
	</div>
 </section>

<jsp:include page="/WEB-INF/include/footer.jsp" />