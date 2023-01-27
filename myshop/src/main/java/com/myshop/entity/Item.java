package com.myshop.entity;

import javax.persistence.*;

import com.myshop.constant.ItemSellStatus;
import com.myshop.dto.ItemFormDto;
import com.myshop.exception.OutofStockException;

import lombok.*;

@Entity //클래스를 엔티티로 선언
@Table(name="item") //Table: 엔티티와 매핑할 테이블을 지정, (name="item"): 테이블명 설정, 설정을 따로 안하면 클래스명으로 설정됨
@Getter
@Setter
@ToString
public class Item extends BaseEntity { //컬럼
	//not null이 아닐때는 필드 타입을 객체(예 int -> Integer)로 지정해야 한다.
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 설정, 엔티티 클래스는 반드시 기본키를 가져야 한다
	private Long id; //상품코드
	
	@Column(nullable=false, length=50)
	private String itemNm; //상품명
	
	@Column(nullable=false, name="price")
	private int price; //상품가격
	
	@Column(nullable=false)
	private int stockNumber; //재고수량
	
	@Lob //대형데이터 저장
	@Column(nullable=false) //nullable=false: NOT NULL
	private String itemDetail; //상품 상세 설명
	
	@Enumerated(EnumType.STRING) //enum클래스 매핑
	private ItemSellStatus itemSellStatus; //상품 판매상태
	
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
	}
	
	//상품재고 감소
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber; //주문 후 남은 재고수량
		
		if(restStock < 0) {
			throw new OutofStockException("상품의 재고가 부족 합니다.(현재 재고 수량: " + this.stockNumber + ")");
		}
		
		this.stockNumber = restStock; //주문 후 남은 재고수량을 상품의 현재 재고 값으로 할당
	}
	
	//상품재고 증가(주문취소 시)
	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
	}
}
