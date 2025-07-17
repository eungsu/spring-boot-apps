package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.UserService;
import com.example.demo.web.form.SignupForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("signupForm") SignupForm signupForm,
			BindingResult errors,
			RedirectAttributes redirectAttributes,
			Model model) {
		
		if (errors.hasErrors()) {
			model.addAttribute("validated", true);
			model.addAttribute("errors", errors);
			return "signup";
		}
		
		userService.signup(signupForm);
		redirectAttributes.addFlashAttribute("username", signupForm.getUsername());
		redirectAttributes.addFlashAttribute("nickname", signupForm.getNickname());
		
		return "redirect:/complete";
	}
	
	@GetMapping("/complete")
	public String complete() {
		return "complete";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		model.addAttribute("errorCode", 403);
		model.addAttribute("errorMessage", "접근권한이 부족합니다.");
		
		return "error-page";
	}
	
}
