package de.tr.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.tr.dao.CustomerDao;
import de.tr.dao.DeleteException;
import de.tr.dao.DuplicateEntryException;
import de.tr.model.Customer;

/**
 * The Controller class for {@link Customer} objects. Its tasks:
 * <ul>
 * <li>read and verify the parameters</li>
 * <li>access to the objects of the system</li>
 * <li>selection of the view and provision of content for the view</li>
 * </ul>
 * 
 * @author 
 * 
 */
@Controller
public class CustomerController {
	private static Logger log = Logger.getLogger(CustomerController.class);

	CustomerDao customerDao = null;
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * Its task is to create login page information for customer and set a view page for login.
	 * <p>
	 * Expected HTTP GET and request '/login.html'.
	 * </p>
	 * 
	 * @return View name 'login' selecting view
	 *         '/view/login.jsp'.
	 */
	@RequestMapping("/login.html")
	public ModelAndView loginForm() {
		log.info("login");
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Login");             // setting value 'Login' for attribute 'title'. This title value will be shown in the view page.
		mv.addObject(
				"message1",
				"Please provide login information");   // setting value for attribute 'message1'. This message1 value will be shown in the view page.
		mv.addObject(
				"username",
				"User Name");						// setting value for attribute 'username'. This username value will be shown in the view page.
		mv.addObject(
				"password",
				"Password");						// setting value for attribute 'password'. This password value will be shown in the view page.
		mv.setViewName("login");					// setting view name 
		return mv;
	}

	
	/**
	 * Its task is to check customer's login credential.
	 * If login is successful,set a success message and view name is set to login-success. 
	 * If login is not successful, set the error message and set view name to login.
	 * <p>
	 * Expected HTTP POST and request '/login-check.html'.
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @param session
	 *  
	 * @return For failed login, View name 'login' selecting view
	 *         '/view/login.jsp' or for successful login view name 'login' selecting view
	 *         '/view/login.jsp'.
	 */
	@RequestMapping(value= "/login-check.html" , method = RequestMethod.POST)
	public ModelAndView loginCheck(String username,String password,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		/*As user name is unique, find the customer by its username. 
		customerList will either contain one customer object or no customer object.
		if customerList size is 0 , we can say that there is no customer with the given username. 
		So we set a error message 'username doest not exist'. If customerList size is not 0, this means, username exists.
		Then, we check whether the given password matches for that user. If matches, we keep the customer object into session.
		If password not matches set the error message and set view name to login.jsp 
		 */ 
		List<Customer> customerList = customerDao.findByUsername(username);          //find customer by username
		if(customerList.size()==0){													 //if customerList is 0,enter into this if condition
			mv.addObject("errorMessage", "username does not exist");				 //if username does not exist, set this error message
			mv.addObject("title", "Login");
			mv.addObject("username","Username");
			mv.addObject("password", "Password");
			mv.setViewName("login");
			return mv;
		}else{
			Customer customer = customerList.get(0);								//get the customer object
				if(customer.getPassword().equals(password)){						//check the password matches or not
					session.setAttribute("loggedInCustomer", customer);				//if matches, set the customer object into session
				}else{
					mv.addObject("errorMessage", "password does not match");		//if password does not matches set this error message
					mv.addObject("title","Login");
					mv.addObject("username","Username");
					mv.addObject("password", "Password");
					mv.setViewName("login");
					return mv;
				}			
		}
		mv.addObject("successMessage", "Login Successful");							//if username exists and password matches, set this success message.
		mv.setViewName("login-success");											//if username exists and password matches, set this view name.
		return mv;
	}
	
	
	
	/**
	 * Its task is to create a new {@link Customer} object(while registering or creating customer) 
	 * or to find the {@link Customer} object(while editing customer).
	 * <p>
	 * Expected HTTP GET and request '/create-customer.html'.
	 * </p>
	 * 
	 * @param id
	 * 
	 * @return View name 'create-customer' selecting view
	 *         '/view/create-customer.jsp'.
	 */
	@RequestMapping(value= "/create-customer.html")
	public ModelAndView createCustomer(@RequestParam(required = false) Integer id) {
		log.info("createCustomer");
		ModelAndView mv = new ModelAndView();
			if(id == null){									//id is null for registering. but id is not null for editing
				//setting information for registration form.See the create-customer.jsp page
				mv.addObject(new Customer());
				mv.addObject("title", "Customer Registration");      
				mv.addObject("button", "Register");
			}else{
				//setting information for edit.See the create-customer.jsp page
				mv.addObject(customerDao.findById(id));
				mv.addObject("title", "Edit");
				mv.addObject("button", "Update");
			}
			
		mv.setViewName("create-customer");         //set this view name.
		return mv;
	}
	  
	

	/**
	 * <p>
	 * Saves a {@link Customer} object. Its purpose is to Create or updates a customer.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP POST and request '/saveCustomer.html'.
	 * </p>
	 * 
	 * @param customer
	 * @param model
	 * 
	 * @return View name 'save-customer-success' selecting view
	 *         '/view/save-customer-success.jsp' or View name 'create-customer' selecting view
	 *         '/view/create-customer.jsp'
	 */
	@RequestMapping(value="/saveCustomer.html" , method=RequestMethod.POST)
	public ModelAndView saveCustomer(Customer customer, Model model){
		ModelAndView mv = new ModelAndView();
		int id = customer.getCustomerId();
		if(id == 0){
			mv.addObject("successMessage", "Successfully Registered");			//while registering/creating customer,set this success message
		}else{
			mv.addObject("successMessage", "Successfully Updated");				//while updating customer,set this success message
		}
		
		try{
			customerDao.save(customer);
			mv.setViewName("save-customer-success");				//For successful save or update, set this view
		}catch(DuplicateEntryException de){							//DuplicateEntryException is thrown from CustomerDao class when a customer can not be deleted.
			mv.addObject("errorMessage", de.getMessage());
			mv.addObject("title", "Customer Registration");      
			mv.addObject("button", "Register");
			mv.setViewName("create-customer");						//if error occurs,stay in the same page and show the error message
		}	
				
		return mv;
	}
	
	/**
	 * <p>
	 * Searches for all {@link Customer} objects and returns them in a
	 * <code>List</code>.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/customer-list.html'.
	 * </p>
	 * @param edit
	 * 
	 * @return View name 'customer-list' selecting view
	 *         '/view/customer-list.jsp'.
	 */
	@RequestMapping(value = "/customer-list.html", method = RequestMethod.GET)
	public ModelAndView findAll(@RequestParam(required=false) String edit) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "All Customers");					  //set title
		
		mv.addObject("customerList", customerDao.findAll());      //set customerList

		if(edit != null){
			mv.addObject("edit", true);				//edit distinguishes whether edit and delete link should be provided in customer-list.jsp or not
		}
		
		mv.setViewName("customer-list");			//set view 
		return mv;
	}
	
	
	/**
	 * <p>
	 * Deletes a {@link Customer} object.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/delete-customer.html'.
	 * </p>
	 * 
	 * @param id
	 * @throws DeleteException
	 *             if the specific {@link Customer} is already in use.
	 * @return For successful delete, view name 'delete-success' selecting view
	 *         '/view/delete-success.jsp'. For error, view name 'admin-error' selecting view admin-error.jsp
	 */
	@RequestMapping(value = "/delete-customer.html", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required=false) Integer id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "");									//set title
		mv.addObject("message", "Successfully deleted customer");	//set message
		try{
			customerDao.delete(id);									//passing the argument id to delete the customer object
		}catch(DeleteException de){									//catch DeleteException and set view name and message 
			mv.setViewName("admin-error");
			mv.addObject("message", de.getMessage());
			return mv;
		}
		mv.setViewName("delete-success");							//if delete successful, set this view
		return mv;
	}
	
	
	/**
	 * <p>
	 * Customer edits his profile. If customer is not logged in, redirect to "/login.html".
	 *  If customer is logged in, get the customerId and redirect to "create-customer.html"
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/edit-profile.html'.
	 * </p>
	 * 
	 * @param session
	 * @return redirect to either "login.html" for view "/view/login.jsp" or redirect to "create-customer" for view "/view/create-customer.jsp"
	 */
	@RequestMapping(value = "/edit-profile.html", method = RequestMethod.GET)
	public String editProfile(HttpSession session){
		Customer customer = (Customer)session.getAttribute("loggedInCustomer");     //get loggedInCustomer from session
		if(customer == null){							//if customer object is null, that means no customer has logged in, so redirect to "/login.html"
			return "redirect:login.html";	
		}
		int customerId = customer.getCustomerId();      //if customer object is not null, get the customer Id
		return "redirect:create-customer.html?id="+customerId;      //redirect to "create-customer.html" for edit
	}
	
	
	/**
	 * <p>
	 * sets the view name for license description.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/license-description.html'.
	 * </p>
	 * 
	 * @param session
	 * @return View name 'license-description' selecting view
	 *         '/view/license-description.jsp'.
	 */
	@RequestMapping(value = "/license-description.html", method = RequestMethod.GET)
	public ModelAndView licenseDescription(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("license-description");     //sets view name
		return mv;
	}
	
}
