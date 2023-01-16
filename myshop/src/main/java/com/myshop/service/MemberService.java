package com.myshop.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.entity.Member;
import com.myshop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할, controller와 Repository사이에 있음
@Transactional //서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
public class MemberService implements UserDetailsService { //UserDetailsService: 로그인시 request에서 넘어온 사용자 정보를 받음
	private final MemberRepository memberRepository; //의존성 주입	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		//UserDetail을 인터페이스로 상속받는 User객체에 DB에서 받은 사용자 정보를 반환한다.
		return User.builder()
			   .username(member.getEmail())
			   .password(member.getPassword())
			   .roles(member.getRole().toString())
			   .build();
	}
	
	public Member saveMember(Member member) {
		vaildateDuplicateMember(member);
		return memberRepository.save(member); //member 테이블에 insert
	}
	
	//이메일 중복체크 메소드(이메일로 아이디를 만다는 사이트)
	private void vaildateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

}
