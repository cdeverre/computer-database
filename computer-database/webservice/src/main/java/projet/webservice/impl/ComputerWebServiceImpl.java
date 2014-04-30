package projet.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import projet.model.Computer;
import projet.service.ComputerServices;
import projet.webservice.ComputerWebService;


@WebService(endpointInterface="projet.webservice.ComputerWebService")
public class ComputerWebServiceImpl implements ComputerWebService {

	@Autowired
	private ComputerServices computerServices;
	
	@Override
	public List<Computer> getAll(String pattern, int currentPage,int ElementPerPage,String orderByColumns,boolean orderByType) {
		Sort sort=null;
		if (orderByType) {
			sort=new Sort(Sort.Direction.ASC,orderByColumns);
		} else {
			sort=new Sort(Sort.Direction.DESC,orderByColumns);
		}
		Pageable pageable=new PageRequest(currentPage-1, 13, sort);
		return computerServices.getAll(pattern,pageable);
	}

	@Override
	public void create(Computer computer) {
		computerServices.create(computer);
		
	}

	@Override
	public void update(Computer computer) {
		computerServices.update(computer);
		
	}

	@Override
	public void delete(long id) {
		computerServices.delete(id);
		
	}

	@Override
	public Computer find(long id) {
		return computerServices.find(id);
	}

	@Override
	public int getNumberOfPage(int count) {
		return computerServices.getNumberOfPage(count);
	}

	@Override
	public int count(String pattern) {
		return computerServices.count(pattern);
	}

}
