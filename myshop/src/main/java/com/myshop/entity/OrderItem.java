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
	
	//주문할 상품과 주문 수량을 통해 orderItem객체를 만듦
	public static OrderItem createOrderItem(Item item, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setCount(count);
		orderItem.setOrderPrice(item.getPrice());
		
		item.removeStock(count);
		
		return orderItem;
	}
	
	//주문한 총 가격
	public int getTotalPrice() {
		return orderPrice*count;
	}
	
	//주문 취소
	public void cancel() {
		this.getItem().addStock(count);
	}
}
