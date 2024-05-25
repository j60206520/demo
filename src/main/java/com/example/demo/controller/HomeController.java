package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@PostMapping("remove")
	@ResponseBody
	public String remove(@RequestBody Member member) {
        try {
        	homeService.deleteMember(member);
            return "success";
        } catch (Exception e) {
            return "刪除失敗";
        }

	}
	
	@PostMapping("edit")
	@ResponseBody
	public String edit(@RequestBody Member member, HttpServletRequest req) {
        try {
        	homeService.updateMember(member);
            return "success";
        } catch (Exception e) {
            return "修改失敗";
        }

	}

}
