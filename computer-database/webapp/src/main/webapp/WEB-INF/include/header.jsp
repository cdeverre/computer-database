<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<spring:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="<spring:url value="/css/jquery-ui-1.10.4.custom.min.css"/>">
<link href="<spring:url value="/css/main.css"/>" rel="stylesheet" media="screen">

<script src="<spring:url value="/js/jquery-1.10.2.js"/>"></script>
<script src="<spring:url value="/js/jquery-ui-1.10.4.custom.min.js"/>"></script>
<script src="<spring:url value="/js/jquery.validate.min.js"/>"></script>
<script src="<spring:url value="/js/jquery.ui.datepicker-fr.js"/>"></script>

 <script src="<spring:url value="/js/form-validation.js"/>"></script>
 <script src="<spring:url value="/js/bootstrap.min.js"/>"></script>



 <script>
$(function() {
	$.datepicker.setDefaults($.datepicker.regional[ "<spring:message code="date.language" />" ] );
	$( ".datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true,
 		dateFormat: "<spring:message code="date.format" />"
		});
	
});


$(document).ready(function(){
	
	$.validator.addMethod("dateFormat",function(value, element) {
                return value.match(/^$/) || value.match(/(0[1-9]|[12][0-9]|3[01])[- \/.](0[1-9]|1[012])[- \/.](19|20)\d\d/)
                	|| value.match(/(0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])[- \/.](19|20)\d\d/);
    }, "<spring:message code="date.error" />");
	
	$('.form-validation').validate(
    {
	    rules: {
	    	
	    	dateIntroduced: {
	    		dateFormat:true
	    	},
	    	
		    name: {
			    minlength: 2,
			    required: true
		    },
    
	    	
	    	
	    	dateDiscontinued : {
	    		dateFormat:true
	    	}
    
		    
	    },
	    highlight: function(element) {
			 $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			 
		},
		
		success: function(element) {
			 $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		}
    });
}); 
</script>


</head>
<body>
	<header class="navbar navbar-inverse navbar-static-top">
		<div class="text-right">
			<a href="Dashboard?language=en"><img src="css/images/english.png"/></a>
			<a href="Dashboard?language=fr"><img src="css/images/francais.png"/></a>
		</div>
		<h1 class="fill">
			<a href="Dashboard"> Application - Computer Database </a>
		</h1>
		
	</header>
	
	