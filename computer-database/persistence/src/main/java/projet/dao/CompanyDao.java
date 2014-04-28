package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Company;


public interface CompanyDao extends JpaRepository<Company,Long> {

}