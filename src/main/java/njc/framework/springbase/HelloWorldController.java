package njc.framework.springbase;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


public class HelloWorldController {

	private static Logger logger = Logger.getLogger(HelloWorldController.class);
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hello")
	public ModelAndView index() {
		logger.info("index start");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("hello/hello");
		view.addObject("message", "Hello World");
		view.addObject("currentDate", new Date());
		
		logger.info("index end");
		return view;
	}
}
