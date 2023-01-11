package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	/*
	//퀴즈
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

	List<Item> findByPriceBetween(Integer price, Integer price2);
	
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	
	List<Item> findByItemSellStatusNotNull();
	
	List<Item> findByItemDetailEndingWith(String itemDetail);
	//List<Item> findByItemDetailLike(String itemDetail);
	*/
	
	/*
	 * 테이블명과 entity클래스 변수명이랑 대소문자 맞춰서 지정해줘야함!
	 * %:itemDetail%
	 * 파라메터는 @Param("변수명") 써주어야함!
	 */
	//@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	//List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	/*
	 * %?1%
	 * 파라메터 순서를 ?뒤에 붙여준다!
	 */
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
	List<Item> findByItemDetail(String itemDetail);
	
	/*
	 * item_detail
	 * 저장되는 컬럼명을 확인해서 이름을 똑같이 해주어야함!
	 */
	@Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	/*
	//퀴 즈
	//(?1) or (:price)
	//@Query("select i from Item i where i.price >= ?1")
	//List<Item> findByPriceGreaterThanEqual(Integer price);
	
	@Query("select i from Item i where i.price >= :price")
	List<Item> getPrice(@Param("price") Integer price);
	
	//@Query("select i from Item i where i.itemNm = ?1 and i.itemSellStatus = ?2")
	//List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

	@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
	List<Item> getItemNmAndItemSellStatus(@Param("itemNm") String itemNm, @Param("sell") ItemSellStatus sell);
	*/
}
