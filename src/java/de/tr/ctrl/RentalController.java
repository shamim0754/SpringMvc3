package de.tr.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.tr.dao.CustomerDao;
import de.tr.dao.RentalDao;
import de.tr.dao.TrailerDao;
import de.tr.model.Customer;
import de.tr.model.Rental;
import de.tr.model.Trailer;


@Controller
public class RentalController {
	
	TrailerDao trailerDao = null;
	CustomerDao customerDao = null;
	RentalDao rentalDao = null;	
	
	
	public TrailerDao getTrailerDao() {
		return trailerDao;
	}


	public void setTrailerDao(TrailerDao trailerDao) {
		this.trailerDao = trailerDao;
	}


	public CustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public RentalDao getRentalDao() {
		return rentalDao;
	}


	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	/**
	 * Its task is to set the rental form page.we check several conditions, customer is logged in or not, 
	 * license category for the logged in customer exists or not and set view according to the condition. 
	 * <p>
	 * Expected HTTP GET and request '/create-rental.html'.
	 * </p>
	 * 
	 * @param request
	 * 
	 * @return View name 'create-trailer' selecting view
	 *         '/view/create-trailer.jsp'.
	 */
	@RequestMapping(value = "/create-rental.html")
	public ModelAndView createRent(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Customer customer = (Customer)request.getSession().getAttribute("loggedInCustomer");    //get loggedInCustomer from session
		if(customer==null){							//if customer object is null, that means no customer has logged in, so set view 'login-request'
			mv.setViewName("login-request");
			return mv;
		}
		String customerName = customer.getFirstName() +" "+ customer.getLastName();    //concatenate customer's first and last name. 
		String licenseCategory = customer.getLicenseCategory();							//get license category
		if(licenseCategory == null){											//if customer has no license category, set the view to 'customer-error'
			mv.setViewName("customer-error");
			mv.addObject("message", "No License Category");
			return mv;
		}
		
		mv.addObject("customerName", customerName);								//set customerName attribute
		mv.addObject("customerId", customer.getCustomerId());					//set customerId attribute
		mv.addObject("title", "Choose a trailer to rent");						//set title attribute
		
		//setting message
		if(licenseCategory.equals("B")){			
				mv.addObject("message", "Trailer towing vehicle and trailer gross weight may only be less equal to 750kg ! " +
						"We control during the transfer driver's license and the vehicle 's license!");
		}else if(licenseCategory.equals("BE")){
				mv.addObject("message","Trailer towing vehicle and trailer gross weight may only be less equal to 3500kg ! " +
						"We control during the transfer driver's license and the vehicle 's license!");
		}else if(licenseCategory.equals("B96")){
				mv.addObject("message", "");
		}
		
		//set trailerList attribute. we pass the licenseCategory into 'availableTrailersLicense' method to get the trailers according to customer's license category.
		mv.addObject("trailerList",trailerDao.availableTrailersInLicense(licenseCategory));			
		mv.setViewName("create-rental");					//set view
		return mv;
	}
	
	
	/**
	 * Its task is to save rental information. Information like which customer buys which trailer is saved and updates the trailer's status from available to rented.
	 * <p>
	 * Expected HTTP POST and request '/saveRent.html'.
	 * </p>
	 * 
	 * @param rental
	 * @param customerId
	 * @param trailerId
	 * @param model
	 *  
	 * @return View name 'save-rental-success' selecting view
	 *         '/view/save-rental-success.jsp'.
	 */
	@RequestMapping(value = "/saveRent.html" , method = RequestMethod.POST)
	public ModelAndView saveRent(Rental rental,int customerId,int trailerId,Model model){
		ModelAndView mv = new ModelAndView();
		if(rental.getDateOfRent()==null){
			rental.setDateOfRent(new Date());				//setting the date of rental
		}
		Trailer trailer = trailerDao.findById(trailerId);		//find the trailer by its id
		Customer customer = customerDao.findById(customerId);	//find the customer by its id
		rental.setCustomer(customer);
		rental.setTrailer(trailer);
		rentalDao.save(rental); 			//saving rental
		trailer.setStatus(1);				//setting trailer's status from available to rented
		trailerDao.save(trailer);        	//updating trailer
		
		mv.addObject("successMessage","You have successfully rented this trailer");     //set success message
		mv.setViewName("save-rental-success");				// set view
		return mv;
	}
	
	/**
	 * <p>
	 * Searches for all {@link Rental} objects and returns them in a
	 * <code>List</code>.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/rental-list.html'.
	 * </p>
	 * 
	 * @param session
	 * @return View name 'rental-list' selecting view
	 *         '/view/rental-list.jsp'.
	 */
	@RequestMapping(value = "/rental-list.html" , method = RequestMethod.GET)
	public ModelAndView findAll(HttpSession session){
		ModelAndView mv = new ModelAndView();
		Customer customer = (Customer)session.getAttribute("loggedInCustomer");			//get loggedInCustomer from session
		if(customer == null){							//if customer object is null, that means no customer has logged in, then set the view to 'login-request'
			mv.setViewName("login-request");
			return mv;
		}
		List<Rental> rentalList = rentalDao.findAll(customer);		//find a customers all rental information
		mv.addObject("title", "All Rented Trailers");				//set title
		mv.addObject("rentalList", rentalList);						//set the rental list
		mv.setViewName("rental-list");								//set view
		return mv;			
	}
	
	
	/**
	 * <p>
	 * Return trailer and set the date of return and set {@link Trailer} status from rented to available
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/return-trailer.html'.
	 * </p>
	 * 
	 * @param rentalId
	 * @param trailerId
	 * @return View name 'return-rental-success' selecting view
	 *         '/view/return-rental-success.jsp'.
	 */
	@RequestMapping(value = "/return-trailer.html" , method = RequestMethod.GET)
	public ModelAndView returnTrailer(int rentalId,int trailerId){
		ModelAndView mv = new ModelAndView();
		Rental rental = rentalDao.findById(rentalId);			    //find the rental object
		Trailer trailer = trailerDao.findById(trailerId);			//find the trailer object
		
		if(rental.getDateOfReturn() == null){
			rental.setDateOfReturn(new Date()); 					//setting the date of return
		}
		rentalDao.save(rental);										//update the rental information
		
		trailer.setStatus(0);										//setting trailer status from rented to available
		trailerDao.save(trailer);									//updating trailer information
		mv.addObject("successMessage", "Successfully returned rental");      //set success message
		mv.addObject("rentalId",rentalId);							//set rental id
		mv.setViewName("return-rental-success");					//set view
		return mv;
		
	}
}
