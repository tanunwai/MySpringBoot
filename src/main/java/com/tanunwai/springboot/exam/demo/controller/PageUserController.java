package com.tanunwai.springboot.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanunwai.springboot.exam.demo.dto.APIResponse;
import com.tanunwai.springboot.exam.demo.entity.User;
import com.tanunwai.springboot.exam.demo.service.PageUserService;

@Controller
@RequestMapping(path = { "/page" })
public class PageUserController {

	@Autowired
	PageUserService pageUserService;

	@RequestMapping(path = { "/" })
	public String viewHomePage(Model model) {
		return viewPage(model, 1, "name", "asc");
	}

	@RequestMapping(path = { "/pagination/{pageNum}" })
	public String viewPage(Model model, @PathVariable(value = "pageNum") int pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {
		Page<User> pages = pageUserService.listAll(pageNum, sortField, sortDir);
		List<User> listPage = pages.getContent();
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalItems", pages.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listusers", listPage);
		return "userlist2";
	}
	
	@RequestMapping(path = { "/paginationandsourt/{offset}/{pageSize}/{field}" })
	@ResponseBody
	public APIResponse<Page<User>> getUserWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		Page<User> pages=pageUserService.findUserWithPaginationAndSorting(offset, pageSize, field);		
		return new APIResponse<>(pages.getSize(), pages);
	}
}
