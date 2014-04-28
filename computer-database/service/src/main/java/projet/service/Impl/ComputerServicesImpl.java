package projet.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projet.dao.ComputerDao;
import projet.dao.LogDao;
import projet.exception.ComputerNonexistentException;
import projet.model.Computer;
import projet.model.Log;
import projet.service.ComputerServices;

@Service
@Transactional
public class ComputerServicesImpl implements ComputerServices {
	
	
	private static Logger logger = LoggerFactory.getLogger(ComputerServicesImpl.class);
	
	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private LogDao logDao;
	
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerServicesImpl() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	@Transactional(readOnly=false)
	public void create(Computer computer) {
		computerDao.save(computer);
		Log log=new Log(computer.getId(),"Creating");
		logDao.save(log);
		
		logger.debug("Computer created succesfully");
	}
	
	
	@Transactional(readOnly=false)
	public void update(Computer computer) {
		computerDao.save(computer);
		Log log=new Log(computer.getId(),"Updating");
		logDao.save(log);
			
		logger.debug("Computer updated succesfully");
		
	}

	@Transactional(readOnly=false)
	public void delete(long id) {
	
		computerDao.delete(id);
		Log log=new Log(id,"Deleting");
		logDao.save(log);

		logger.debug("Computer deleted succesfully");
	}
		
	@Transactional(readOnly=true)
	public Computer find(long id) {
		Computer c=computerDao.findOne(id);
		if (c==null) {
			throw new ComputerNonexistentException();
		}
		return (c);
	}
	

	@Transactional(readOnly=true)
	public int getNumberOfPage(int count) {
		return (int)Math.ceil((double)count/(double)13);
	}

	@Transactional(readOnly=true)
	public List<Computer> search(String pattern, Pageable pageable) {
		return computerDao.findByNameContainingOrCompanyNameContaining(pattern, pattern,pageable).getContent();

	}

	@Transactional(readOnly=true)
	public int count(String pattern) {
		return computerDao.countByNameContainingOrCompanyNameContaining(pattern, pattern).intValue();
	}
	
	
	
}
