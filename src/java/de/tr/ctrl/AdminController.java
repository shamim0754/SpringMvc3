package de.tr.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Admin Controller class. Its tasks:
 * <ul>
 * <li>read and verify the parameters</li>
 * <li>access to the objects of the system</li>
 * <li>selection of the view and provision of content for the view</li>
 * </ul>
 * 
 * @author 
 */

@Controller
public class AdminController {

	private static Logger log = Logger.getLogger(HomeController.class);

	/**
	 * Custom handler for the admin-home view. This method sets the homepage for 
	 * admin and keeps some value into session. Note that this handler relies on
	 * the RequestToViewNameTranslator to determine the logical view name based
	 * on the request URL: "/admin.html"
	 * 
	 * @return View name "admin-home" selecting view "/view/admin-home.jsp"
	 */
	@RequestMapping("/admin.html")
	public ModelAndView index(HttpServletRequest request) {
		log.info("welcome");
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Admin area");                 // setting value 'Admin area' for attribute 'title'. This title value will be shown in the view page.
		mv.setViewName("admin-home");						 // setting view name
		request.getSession().setAttribute("admin", true);    // to indicate admin has logged in . When admin logs out this attribute is removed.
		request.getSession().removeAttribute("loggedInCustomer");   //as admin is logging in, we are removing loggedinCustomer's info from session, when admin logs out next time customer needs to log in again
		return mv;
	}

}