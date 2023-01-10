package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

//DAO역할을 하는 Repository 인터페이스
//JpaRepository: 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의 되어있다.
//JpaRepository<사용할 엔티티 클래스, 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long>{
	//쿼리 메소드 네이밍 롤
	//find + (엔티티이름) + By + 변수이름
	//sql = select * from item where item_nm = ?
	List<Item> findByItemNm(String itemNm);
	
	//sql = select * from item where item_nm = ? or item_detail = ?
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

	//sql = select * from item where price < ?
	List<Item> findByPriceLessThan(Integer price);
	
	//sql = select * from item where price < ? order by price desc
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

	List<Item> findByPriceBetween(Integer price, Integer price2);
	
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	
	List<Item> findByItemSellStatusIsNotNull();
	
	List<Item> findByItemDetailEndingWith(String itemDetail);
}
