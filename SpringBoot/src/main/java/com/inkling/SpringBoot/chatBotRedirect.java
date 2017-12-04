package com.inkling.SpringBoot;

import java.io.IOException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class chatBotRedirect {
	
	@RequestMapping(method = RequestMethod.POST, value = "/chatBot")
	public ModelAndView chatBot() throws IOException{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("chatBot");
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/carousel")
	public ModelAndView carousel() throws IOException{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("carouseldemo");
		return view;
	}

}
