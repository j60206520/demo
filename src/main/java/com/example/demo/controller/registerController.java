package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.service.RegisterService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class registerController {

	@Autowired
	private RegisterService registerService;

	@PostMapping("register")
	@ResponseBody
	public String register(@RequestBody Member member, HttpServletRequest req) {
		try {
			registerService.saveMember(member);
			req.getSession().setAttribute("fullName", member.getFullName());
			req.getSession().setAttribute("username", member.getUsername());
			req.getSession().setAttribute("password", member.getPassword());
			return "success";
		} catch (RuntimeException e) {
			return e.getMessage();
		}

	}

}
