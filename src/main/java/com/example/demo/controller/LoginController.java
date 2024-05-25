package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("loginPage")
	public String loginPage() {
		return "loginPage";
	}

	@GetMapping("registerPage")
	public String registerPage() {
		return "registerPage";
	}
	
	
	@GetMapping("homePage")
	public String homePage() {
		return "homePage";
	}
	
	@PostMapping("login")
	@ResponseBody
	public String login(@RequestBody Member member, HttpServletRequest req) {
        try {
        	Member m = loginService.findMember(member);
            req.getSession().setAttribute("fullName", m.getFullName());
            req.getSession().setAttribute("username", m.getUsername());
            req.getSession().setAttribute("password", m.getPassword());
            return "success";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
	}
}
