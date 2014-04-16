$(document).ready(function(){
	
	$.validator.addMethod("dateFormat",function(value, element) {
                return value.match(/^$/) || value.match(/(0[1-9]|[12][0-9]|3[01])[- \/.](0[1-9]|1[012])[- \/.](19|20)\d\d/);
    }, "Please enter a date in the format dd-mm-yyyy.");
	
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