package com.tanunwai.springboot.exam.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path= {"/test"})
public class MessageController {

	@GetMapping(path= {"/message"})
	public String index(Model model) {
		List<Integer> scores=Arrays.asList(80, 47, 90, 100, 55);
		model.addAttribute("name", "John");
		model.addAttribute("age", "<h1>18</h1>");
		model.addAttribute("scores", scores);
		return "message";
	}
}
