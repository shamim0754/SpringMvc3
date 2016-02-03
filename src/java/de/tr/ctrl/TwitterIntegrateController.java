package de.tr.ctrl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import de.tr.model.Twitters;

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
public class TwitterIntegrateController {
	private static Logger log = Logger
			.getLogger(TwitterIntegrateController.class);

	/**
	 * Custom handler for the twitter form view. Note that this handler relies
	 * on the RequestToViewNameTranslator to determine the logical view name
	 * based on the request URL: "/post-tweet.html"
	 * 
	 * @return View name 'twitter-form' selecting view '/view/twitter-form.jsp'
	 * @throws TwitterException
	 */
	@RequestMapping(value = "/post-tweet.html", method = RequestMethod.GET)
	public ModelAndView twitterForm() throws TwitterException {
		log.info("twitter integration");

		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Twitter Post");
		mv.addObject("button", "Post");
		mv.setViewName("twitter-form");
		return mv;
	}

	/**
	 * <p>
	 * Expected HTTP POST and request '/post-tweet.html'.
	 * </p>
	 * 
	 * @param tweets
	 * @param model
	 * @return View name 'twitter-form' selecting view '/view/twitter-form.jsp'.
	 */
	@RequestMapping(value = "/post-tweet.html", method = RequestMethod.POST)
	public ModelAndView submitTwiter(Twitters tweets, Model model)
			throws TwitterException {
		log.info("twitter integration");
		ModelAndView mv = new ModelAndView();

		// twitter configuration
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("");
		cb.setOAuthConsumerSecret("");
		cb.setOAuthAccessToken("");
		cb.setOAuthAccessTokenSecret("");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		// send tweets message
		twitter.updateStatus(tweets.getTweetsText());				

		//setting information for view page twitter-form.jsp
		mv.addObject("title", "Twitter Post");			
		mv.addObject("button", "Post");
		mv.addObject("success", "Sucessfully tweets in your account");
		mv.setViewName("twitter-form");
		return mv;
	}

}
