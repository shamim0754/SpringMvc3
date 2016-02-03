package de.tr.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.tr.model.Customer;
import de.tr.model.Rental;
import de.tr.model.Trailer;

/**
 * The Data access class for {@link Trailer} objects. DAO encapsulates all accesses
 * to the data source, including construction and dismantling of the connection.
 * All Interaction with the database regarding the entity bean {@link Trailer}
 * should be handled by this class! Its tasks:
 * <ul>
 * <li>set new objects of the class</li>
 * <li>modify or delete an object of the class</li>
 * <li>deliver all objects of the class</li>
 * <li>further functions in context to the class objects</li>
 * </ul>
 * 
 * @author 
 */
public class TrailerDao extends HibernateDaoSupport{

	/**
	 * Saves {@link Trailer} object specified by the parameter in the DB
	 * 
	 * @param trailer
	 *            is the object that will be saved in the DB
	 * @return a {@link Trailer} object
	 */
	public Trailer save(Trailer trailer){
		HibernateTemplate template = getHibernateTemplate();
		template.saveOrUpdate(trailer);
		return trailer;
	}
	
	/**
	 * Returns all {@link Trailer} objects from the DB
	 * 
	 * @return a list of {@link Trailer} objects
	 */
	public List<Trailer> findAll(){
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(Trailer.class);
		
	}
	
	/**
	 * Returns a single {@link Trailer} object by its id
	 * 
	 * @param id
	 *            the primary key of {@link Trailer}
	 * @return a single {@link Trailer}
	 */
	public Trailer findById(int id){
		HibernateTemplate template = getHibernateTemplate();
		return template.get(Trailer.class, id);
	}
	
	/**
	 * Deletes the specified {@link Trailer} object from the DB
	 * 
	 * @param id
	 *            this id's object to be deleted
	 * @throws DeleteException
	 *             if the specified {@link Trailer} is included in a {@link Rental}
	 */
	public void delete(int id) throws DeleteException{
		HibernateTemplate template = getHibernateTemplate();
		Trailer trailer = findById(id);         //find the trailer object 
		try{
			template.delete(trailer);
		}catch(Exception de){
			throw new DeleteException("Selected trailer can not be deleted");
		}	
	}
	
	
	/**
	 * Finds all available {@link Trailer} objects from the DB according to {@link Customer} license
	 * 
	 * @param licenseCategory
	 *           
	 * @throws DeleteException
	 *             if the specified {@link Trailer} is included in a {@link Rental}
	 */
	@SuppressWarnings("unchecked")
	public List<Trailer> availableTrailersInLicense(String licenseCategory){
		HibernateTemplate template = getHibernateTemplate();
		int carryCapacityMin = 0;
		int carryCapacityMax = 0;
		
		
		//set carryCapacityMin and carryCapacityMax according to license category
		if(licenseCategory.equals("B")){			
			carryCapacityMin = 1;
			carryCapacityMax = 750;
		}else if(licenseCategory.equals("BE")){
			carryCapacityMin = 1;
			carryCapacityMax = 3500;
		}else if(licenseCategory.equals("B96")){
			carryCapacityMin = 1;
			carryCapacityMax = 4250;
		}
		
		//return the available trailers according to carrying capacity
		return template.find("From Trailer tr where (tr.carryingCapacity between "+carryCapacityMin+" and " +carryCapacityMax+ ") and status=" + 0 );
		
	}
	
}
