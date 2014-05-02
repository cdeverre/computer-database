package projet.webservice.impl;

import org.springframework.beans.factory.annotation.Autowired;

import projet.model.Computer;
import projet.service.ComputerServices;
import projet.webservice.ComputerWebService;


public class ComputerWebServiceImpl implements ComputerWebService {

	@Autowired
	private ComputerServices computerServices;
	
	@Override
	public Computer find(long id) {
		return computerServices.find(id);
	}


}
