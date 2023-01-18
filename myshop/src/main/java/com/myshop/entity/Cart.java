package com.myshop.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="cart")
@Getter
@Setter
@ToString
public class Cart {
	
	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//일대일 단방향 매핑 (Cart가 Member를 참조한다)
	//자식테이블(자식엔티티)에 적어주면됨
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	
}
