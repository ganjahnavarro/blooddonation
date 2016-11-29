package xzvf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xzvf.Utility;
import xzvf.model.User;
import xzvf.service.UserService;


@Controller
@RequestMapping("/")
public class ApplicationController {
	
	@Autowired UserService userService;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("message", "Hello World!");
		return "app/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "app/login";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap model) {
		User user = userService.findByUsername(Utility.getSecurityPrincipal());
		model.addAttribute("user", user.getFirstName());
		return "app/dashboard";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			model.addAttribute("infoMessage", "You have been logged out.");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "app/accessdenied";
	}
	
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String todo() {
		return "app/todo";
	}
	
}
