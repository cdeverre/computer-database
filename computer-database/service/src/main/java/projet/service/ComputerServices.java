package projet.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projet.dao.ComputerDao;
import projet.dao.LogDao;
import projet.exception.ComputerNonexistentException;
import projet.model.Computer;

@Service
@Transactional
public class ComputerServices {
	
	
	private static Logger logger = LoggerFactory.getLogger(ComputerServices.class);
	
	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private LogDao logDao;
	
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerServices() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	@Transactional(readOnly=false)
	public void create(Computer computer) {
		computerDao.create(computer);
		logDao.insertLog(computer.getId(),"Creating");
		
		logger.debug("Computer created succesfully");
	}
	
	
	@Transactional(readOnly=false)
	public void update(Computer computer) {
		computerDao.update(computer);
		logDao.insertLog(computer.getId(),"Updating");
			
		logger.debug("Computer updated succesfully");
		
	}

	@Transactional(readOnly=false)
	public void delete(long id) {
	
			computerDao.delete(id);
			logDao.insertLog(id,"Deleting");

			logger.debug("Computer deleted succesfully");
	}
		
	@Transactional(readOnly=true)
	public Computer find(long id) {
		Computer c=computerDao.find(id);
		if (c==null) {
			throw new ComputerNonexistentException();
		}
		return (computerDao.find(id));
	}
	
	@Transactional(readOnly=true)
	public int count() {
		return (computerDao.count());
	}

	@Transactional(readOnly=true)
	public ArrayList<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		return (computerDao.getAllPagination(currentPage,orderByColumns, orderByType));
	}

	@Transactional(readOnly=true)
	public int getNumberOfPage(int count) {
		return (int)Math.ceil((double)count/(double)ComputerDao.LIMIT);
	}

	@Transactional(readOnly=true)
	public ArrayList<Computer> search(String pattern, int currentPage,String orderByColumns,boolean orderByType) {
		return computerDao.search(pattern,currentPage,orderByColumns,orderByType);

	}

	@Transactional(readOnly=true)
	public int count(String pattern) {
		return (computerDao.count(pattern));
	}
	
	
	
}