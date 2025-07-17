package com.example.demo.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ListDto;
import com.example.demo.service.PostService;
import com.example.demo.util.Markdown;
import com.example.demo.vo.Post;
import com.example.demo.web.form.CommentCreateForm;
import com.example.demo.web.form.PostCreateUpdateForm;
import com.example.demo.web.security.SecurityUser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final Markdown markdown;
	private final PostService postService;
	
	@GetMapping("/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page, 
			@RequestParam(name = "keyword", required = false) String keyword,
			Model model) {
		
		ListDto<Post> dto = postService.getPosts(page, keyword);
		model.addAttribute("posts", dto.getItems());
		model.addAttribute("paging", dto.getPaging());
		
		return "posts/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int postNo, Model model) {
		model.addAttribute("markdown", markdown);
		model.addAttribute("post", postService.getPostDetail(postNo));
		model.addAttribute("comments", postService.getComments(postNo));
		
		return "posts/detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete")
	public String delete(@RequestParam("no") int postNo,
			@AuthenticationPrincipal SecurityUser securityUser) {
		postService.deletePost(postNo, securityUser.getUser().getNo());
		
		return "redirect:/posts/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String form(Model model) {
		model.addAttribute("postCreateForm", new PostCreateUpdateForm());
		
		return "posts/form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("postCreateForm") PostCreateUpdateForm form,
			BindingResult errors,
			@AuthenticationPrincipal SecurityUser securityUser,
			Model model) {
		
		if (errors.hasErrors()) {
			model.addAttribute("validated", true);
			model.addAttribute("errors", errors);
			
			return "posts/form";
		}
		
		postService.createPost(form, securityUser.getUser().getNo());
		
		return "redirect:/posts/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update")
	public String updateform(@RequestParam("no") int postNo,
			@AuthenticationPrincipal SecurityUser securityUser,
			Model model) {
		
		PostCreateUpdateForm postUpdateForm = postService.getUpdateForm(postNo, securityUser.getUser().getNo());
		model.addAttribute("postUpdateForm", postUpdateForm);
		
		return "posts/update";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("postUpdateForm") PostCreateUpdateForm form,
			BindingResult errors,
			@AuthenticationPrincipal SecurityUser securityUser,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if (errors.hasErrors()) {
			model.addAttribute("validated", true);
			model.addAttribute("errors", errors);
			
			return "posts/update";
		}
		
		postService.updatePost(form, securityUser.getUser().getNo());
		redirectAttributes.addAttribute("no", form.getNo());
		
		return "redirect:/posts/detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/comments/add")
	public String addComment(@ModelAttribute CommentCreateForm form,
			@AuthenticationPrincipal SecurityUser securityUser,
			RedirectAttributes redirectAttributes) {
		
		postService.addComment(form, securityUser.getUser().getNo());
		redirectAttributes.addAttribute("no", form.getPostNo());
		
		return "redirect:/posts/detail#comment-form";
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/comments/delete")
	public String deleteComment(@RequestParam int commentNo, 
			@RequestParam int postNo,
			@AuthenticationPrincipal SecurityUser securityUser,
			RedirectAttributes redirectAttributes) {
		
		postService.deleteComment(commentNo, securityUser.getUser().getNo());
		redirectAttributes.addAttribute("no", postNo);
		
		return "redirect:/posts/detail";
	}
	
}
