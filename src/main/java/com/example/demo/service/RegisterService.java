package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.entity.Member;

@Service
public class RegisterService {
	@Autowired
	private MemberRepository memberRepository;

	public Member saveMember(Member member) {
		// 檢查帳號是否重複
		if (memberRepository.findByUsername(member.getUsername()) != null) {
			throw new RuntimeException("帳號已存在");
		}
		return memberRepository.save(member);
	}
}
