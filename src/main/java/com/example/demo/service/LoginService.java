package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.entity.Member;

@Service
public class LoginService {
	@Autowired
	private MemberRepository memberRepository;

	public Member findMember(Member member) {
		Member m = memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword());
		
		// 檢查帳號是否重複
		if (m == null) {
			throw new RuntimeException("帳號不存在");
		} else {
			return m;
		}
	}
}
