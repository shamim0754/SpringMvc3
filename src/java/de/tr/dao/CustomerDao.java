package de.tr.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.tr.model.Customer;
import de.tr.model.Rental;

/**
 * The Data access class for {@link Customer} objects. DAO encapsulates all accesses
 * to the data source, including construction and dismantling of the connection.
 * All Interaction with the database regarding the entity bean {@link Customer}
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
public class CustomerDao extends HibernateDaoSupport{

	/**
	 * Saves {@link Customer} object specified by the parameter in the DB
	 * 
	 * @param customer
	 *            is the object that will be saved in the DB
	 * @return a {@link Customer} object
	 */
	public Customer save(Customer customer) throws DuplicateEntryException{
		HibernateTemplate template = getHibernateTemplate();
		try{
		template.saveOrUpdate(customer);
		}catch(Exception ex){
			throw new DuplicateEntryException("User Name already exists");
		}
		
		return customer;
	}
	
	/**
	 * Returns all {@link Customer} objects from the DB
	 * 
	 * @return a list of {@link Customer} objects
	 */
	public List<Customer> findAll(){
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(Customer.class);
		
	}
	
	/**
	 * Returns a single {@link Customer} object by its id
	 * 
	 * @param id
	 *            the primary key of {@link Customer}
	 * @return a single {@link Customer}
	 */
	public Customer findById(int id){
		HibernateTemplate template = getHibernateTemplate();
		return template.get(Customer.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> findByUsername(String username){
		HibernateTemplate template = getHibernateTemplate();
		return template.find("FROM Customer cus where cus.userName like '"
				+ username +"'");
		
	}
	
	/**
	 * Deletes the specified {@link Customer} object from the DB
	 * 
	 * @param id
	 *            this id's object to be deleted
	 * @throws DeleteException
	 *             if the specified {@link Customer} is included in a {@link Rental}
	 */
	public void delete(int id) throws DeleteException{
		HibernateTemplate template = getHibernateTemplate();
		Customer customer = findById(id);
		try{
			template.delete(customer);
		}catch(Exception de){
			throw new DeleteException("Customer can not be deleted");
		}	
	}
	
}
