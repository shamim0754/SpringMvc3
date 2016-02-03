package de.tr.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * The Home Controller class. Its tasks:
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
public class HomeController {
	private static Logger log = Logger.getLogger(HomeController.class);

	/**
	 * Custom handler for the welcome view/customer home page. Note that this handler relies on the
	 * RequestToViewNameTranslator to determine the logical view name based on
	 * the request URL: "/Index.html"
	 * 
	 * @param request
	 * @return View name 'customer-home' selecting view '/view/customer-home.jsp'
	 */
	@RequestMapping("/Index.html")
	public ModelAndView index(HttpServletRequest request) {
		log.info("welcome");
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Trailer Rental");			// setting value 'Trailer Rental' for attribute 'title'. This title value will be shown in the view page.
		mv.addObject(
				"message1",
				"Welcome to Trailer Rental portal.Here you can rent trailer of your choice.");      //setting value for attribute 'message1'. This message1 value will be shown in the view page.
		mv.setViewName("customer-home");
		
		//admin attribute was set in AdminController.java. To identify admin is not logged in or has logged out, admin attribute is removed. See 'index' method of AdminController class for more details. 
		request.getSession().removeAttribute("admin");      
		
		return mv;
	}
	
}
