package projet.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("AdminForm")
public class AdminForm {
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView createForm(@RequestParam(value = "error", required = false) String error) {
		ModelAndView model;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth instanceof AnonymousAuthenticationToken)) {
		    model = new ModelAndView("adminForm");
			if (error != null) {
				model.addObject("error", "true");
			}
		} else {
			model =new ModelAndView(new RedirectView("Dashboard"));
		}
		
		return model;
	}

}
