package com.example.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.project.service.UserService;
import com.example.project.web.dto.UserRegistrationDto;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@RestController
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
