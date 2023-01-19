package com.myshop.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="order_item")
@Getter
@Setter
@ToString
public class OrderItem extends BaseEntity {
	
	@Id
	@Column(name="order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//다대일 단방향 매핑
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@Column(name="order_price")
	private int orderPrice; //주문가격
	
	private int count; //주문수량
}
