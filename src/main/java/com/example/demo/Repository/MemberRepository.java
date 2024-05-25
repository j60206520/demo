package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByUsername(String username);
	
	Member findByUsernameAndPassword(String username, String password);
	
	Integer deleteByUsername(String username);

}
