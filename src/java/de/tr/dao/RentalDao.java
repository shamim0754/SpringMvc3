package de.tr.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.tr.model.Customer;
import de.tr.model.Rental;

/**
 * The Data access class for {@link Rental} objects. DAO encapsulates all accesses
 * to the data source, including construction and dismantling of the connection.
 * All Interaction with the database regarding the entity bean {@link Rental}
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
public class RentalDao extends HibernateDaoSupport{

	/**
	 * Saves {@link Rental} object specified by the parameter in the DB
	 * 
	 * @param rental
	 *            is the object that will be saved in the DB
	 * @return a {@link Rental} object
	 */
	public Rental save(Rental rental){
		HibernateTemplate template = getHibernateTemplate();
		template.saveOrUpdate(rental);
		return rental;
	}
	
	/**
	 * Returns all {@link Rental} objects from the DB
	 * 
	 * @return a list of {@link Rental} objects
	 */
	@SuppressWarnings("unchecked")
	public List<Rental> findAll(Customer customer){
		HibernateTemplate template = getHibernateTemplate();
		return template.find("From Rental r where r.customer like " +customer.getCustomerId() );
		
	}
	
	
	/**
	 * Returns a single {@link Rental} object by its id
	 * 
	 * @param id
	 *            the primary key of {@link Rental}
	 * @return a single {@link Rental}
	 */
	public Rental findById(int id){
		HibernateTemplate template = getHibernateTemplate();
		return template.get(Rental.class, id);
	}
	
}
