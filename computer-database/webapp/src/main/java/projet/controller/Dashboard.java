package projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.model.Computer;
import projet.service.ComputerServices;
import projet.wrapper.PageWrapper;



@Controller
@RequestMapping("Dashboard")
public class Dashboard  {
	

       
	@Autowired
	private ComputerServices computerServices;
	
	
    /**
     * 
     */
    public Dashboard() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(ModelMap map,
			@RequestParam(value="currentPage",required=false) String currentPage ,
			@RequestParam(value="orderByColumns",required=false) String orderByColumns ,
			@RequestParam(value="orderByType",required=false) String orderByType ,
			@RequestParam(value="pattern",required=false) String pattern,
			@RequestParam(value="add",required=false) String add ,
			@RequestParam(value="delete",required=false) String delete ,
			@RequestParam(value="update",required=false) String update )  {
    	
		int numberOfComputer =0;
		List<Computer> computerList=null;

		PageWrapper page=new PageWrapper(currentPage, orderByColumns, orderByType, pattern);
		
		int numberOfPage=1;
		numberOfComputer=computerServices.count(page.getPattern());
		numberOfPage=Math.max(1,computerServices.getNumberOfPage(numberOfComputer));
		page.setCurrentPage(Math.min(page.getCurrentPage(),numberOfPage));
		computerList = computerServices.search(page);
	
		map.addAttribute("page",page);
		
		map.addAttribute("numberOfPage", numberOfPage );
		map.addAttribute("numberOfComputer", numberOfComputer);
		map.addAttribute("computerList", computerList);
		
		map.addAttribute("add",add);
		map.addAttribute("delete",delete);
		map.addAttribute("update",update);
		
		return("dashboard");
	}

	
	

}
