$(document).ready(function(){
	
	$.validator.addMethod("dateFormat",function(value, element) {
                return value.match(/^$/) || value.match(/(0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])[- \/.](19|20)\d\d/);
    }, "Please enter a date in the format mm-dd-yyyy.");
	
	$('.form-validation').validate(
    {
	    rules: {
		    name: {
			    minlength: 2,
			    required: true
		    },
    
	    	introduced: {
	    			dateFormat:true
	    	},
	    	
	    	discontinued : {
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