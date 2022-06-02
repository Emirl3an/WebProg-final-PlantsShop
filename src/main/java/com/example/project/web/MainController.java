package com.example.project.web;

import com.example.project.global.GlobalData;
import com.example.project.service.CategoryService;
import com.example.project.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@RestController
@Controller
public class MainController {

	@Autowired
	CategoryService categoryService;
	@Autowired
    PlantService plantService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("plants", plantService.getAllPlant());
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("plants", plantService.getAllPlantsByCategoryId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "shop";
	}

	@GetMapping("/shop/viewplant/{id}")
	public String viewPlant(Model model, @PathVariable Long id) {
		model.addAttribute("plant", plantService.getPlantById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "viewPlant";
	}
}
