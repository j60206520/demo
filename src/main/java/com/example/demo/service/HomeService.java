package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.entity.Member;

@Service
public class HomeService {

	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public void deleteMember(Member member) {
		memberRepository.deleteByUsername(member.getUsername());
	}

	@Transactional
	public void updateMember(Member member) {
		Member oldMember = memberRepository.findByUsername(member.getUsername());
		oldMember.setFullName(member.getFullName());
		memberRepository.save(oldMember);

	}
}
