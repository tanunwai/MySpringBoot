package com.tanunwai.springboot.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tanunwai.springboot.exam.demo.entity.User;
import com.tanunwai.springboot.exam.demo.repository.PageUserRepository;

@Service
public class PageUserService {
	@Autowired
	PageUserRepository pageUserRepository;

	public List<User> findAllUsers() {
		return pageUserRepository.findAll();
	}

	public List<User> findUserWithSorting(String field) {
		return pageUserRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<User> findUserWithPagination(int offset, int pageSize) {
		Page<User> users = pageUserRepository.findAll(PageRequest.of(offset, pageSize));
		return users;
	}

	public Page<User> findUserWithPaginationAndSorting(int offset, int pageSize, String field) {
		Page<User> users = pageUserRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return users;
	}
	
	public Page<User> listAll(int pageNum, String sortField, String sortDir){
		Pageable pageable=PageRequest.of(pageNum-1, 5, sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return pageUserRepository.findAll(pageable);
	}
}