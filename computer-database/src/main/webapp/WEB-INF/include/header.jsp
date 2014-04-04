<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="css/jquery-ui-1.10.4.custom.min.css"/>">
<link href="<c:url value="css/main.css"/>" rel="stylesheet" media="screen">

<script src="<c:url value="js/jquery-1.10.2.js"/>"></script>
<script src="<c:url value="js/jquery-ui-1.10.4.custom.min.js"/>"></script>
<script src="<c:url value="js/jquery.validate.min.js"/>"></script>

<script src="<c:url value="js/form-validation.js"/>"></script>
 <script src="<c:url value="js/bootstrap.min.js"/>"></script>



 <script>
$(function() {
	$( ".datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true
		});
});
</script>

</head>
<body>
	<header class="navbar navbar-inverse navbar-static-top">
		<h1 class="fill">
			<a href="Dashboard"> Application - Computer Database </a>
		</h1>
	</header>