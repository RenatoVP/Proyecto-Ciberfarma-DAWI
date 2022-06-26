package com.cibertec.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProyectoController {
	
	@SuppressWarnings("deprecation")
	@GetMapping(value = {"/","/Dashboard"})
	public String Dashboard(Model model) {
		model.addAttribute("now", new Date().getYear() + 1900);
		return "principal";
	}
	
	@GetMapping("/greeting")
	public String gretting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "demosaludo";
	}
	
	@PostMapping("/greeting")
	public String greetingForm(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "demosaludo";
	}
}
