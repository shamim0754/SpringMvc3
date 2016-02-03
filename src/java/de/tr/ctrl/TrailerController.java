package de.tr.ctrl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.tr.dao.DeleteException;
import de.tr.dao.TrailerDao;
import de.tr.model.Trailer;

/**
 * The Controller class for {@link Trailer} objects. Its tasks:
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
public class TrailerController {
	private static Logger log = Logger.getLogger(TrailerController.class);
	private TrailerDao trailerDao = null;
	
	
	public TrailerDao getTrailerDao() {
		return trailerDao;
	}

	public void setTrailerDao(TrailerDao trailerDao) {
		this.trailerDao = trailerDao;
	}

	

	/**
	 * Its task is to create a new {@link Trailer} object(while adding trailer) 
	 * or to find the {@link Trailer} object(while editing trailer).
	 * <p>
	 * Expected HTTP GET and request '/create-trailer.html'.
	 * </p>
	 * 
	 * @param id
	 * 
	 * @return View name 'create-trailer' selecting view
	 *         '/view/create-trailer.jsp'.
	 */
	@RequestMapping(value="/create-trailer.html" , method=RequestMethod.GET)
	public ModelAndView createTrailer(@RequestParam(required=false) Integer id){
		log.debug("/create-trailer.html");
		ModelAndView mv = new ModelAndView();
		if(id == null){											//id is null for creating trailer. But id is not null for editing
			//setting information for creating trailer form.See the create-trailer.jsp page
			mv.addObject(new Trailer());
			mv.addObject("title", "Add Trailer");
			mv.addObject("button", "Add");
		}else{
			//setting information for editing trailer form.See the create-trailer.jsp page
			mv.addObject(trailerDao.findById(id));					//get the trailer by it's id
			mv.addObject("title", "Edit Trailer");
			mv.addObject("button", "Update");
		}
		
		
		mv.setViewName("create-trailer");						//set view to create-trailer
		return mv;
	}
	
	
	/**
	 * <p>
	 * Saves a {@link Trailer} object. Its purpose is to create or update a trailer.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP POST and request '/saveTrailer.html'.
	 * </p>
	 * 
	 * @param trailer
	 * @param model
	 * @return View name 'save-trailer-success' selecting view
	 *         '/view/save-trailer-success.jsp'.
	 */
	@RequestMapping(value="/saveTrailer.html" , method=RequestMethod.POST)
	public ModelAndView saveTrailer(Trailer trailer, Model model){
		log.debug("saveTrailer.html");
		ModelAndView mv = new ModelAndView();
		int id = trailer.getTrailerId();
		if(id == 0){									//to identify whether we are adding or editing
			mv.addObject("successMessage", "Successfully added trailer");     //while adding set this success message
		}else{
			mv.addObject("successMessage", "Successfully Updated trailer");	  //while editing set this success message
		}
		
		trailerDao.save(trailer);						//call the save method of TrailerDao class
		mv.setViewName("save-trailer-success");			//set view to save-trailer-success
		return mv;
	}
	
	/**
	 * <p>
	 * Searches for all {@link Trailer} objects and returns them in a
	 * <code>List</code>.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/trailer-list.html'.
	 * </p>
	 * 
	 * @return View name 'trailer-list' selecting view
	 *         '/view/trailer-list.jsp'.
	 */
	@RequestMapping(value = "/trailer-list.html", method = RequestMethod.GET)
	public ModelAndView findAll(@RequestParam(required=false) String edit) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "All Trailers");				//setting value for attribute title.
		
		mv.addObject("trailerList", trailerDao.findAll());	 //get all the trailers and set it to trailerList.

		if(edit !=null){
			mv.addObject("edit", true);				//edit distinguishes whether edit and delete link should be provided in trailer-list.jsp or not
		}
		mv.setViewName("trailer-list");				//set view to trailer-list
		return mv;
	}
	
	
	/**
	 * <p>
	 * Deletes a {@link Trailer} object.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/delete-trailor.html'.
	 * </p>
	 * 
	 * @param id
	 * @throws DeleteException
	 *             if the specific {@link Trailer} is already in use.
	 * @return View name 'delete-success' selecting view
	 *         '/view/delete-success.jsp'. For error, view name 'admin-error' selecting view admin-error.jsp
	 */
	@RequestMapping(value = "/delete-trailor.html", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required=false) Integer id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Successfully deleted trailor");  //set the success message
		try{
			trailerDao.delete(id);         //delete the trailer
		}catch(DeleteException de){			//if the trailer can not be deleted, set the view to 'admin-error' and set a error message.
			mv.setViewName("admin-error");
			mv.addObject("message", de.getMessage());
			return mv;
		}
		mv.setViewName("delete-success");   //if trailer delete is successful,set view to 'delete-success'.
		return mv;
	}
	
}
