package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {
	
	//Spring이 알아서 관리해줌?
	@Autowired
	ItemRepository itemRepository;
	
	//테스트할 메소드 위에다 @Test를 붙여줌
	//@Test
	//@DisplayName("상품 저장 테스트")
	/* public void createItemTest() {
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		
		//데이터 insert
		Item savedItem = itemRepository.save(item);
		
		System.out.println("객체정보: " + savedItem.toString());
	} */
	
	public void createItemTest() {
		for (int i=1; i<=10; i++) {
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(10000 + i);
		item.setItemDetail("테스트 상품 상세 설명" + i);
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item savedItem = itemRepository.save(item);
		}
	}
	
	/*@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemTest(); //item 테이블에 상품이 insert 됨
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	@DisplayName("상품명 or 상품상세설명 테스트")
	public void findByItemNmOrItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	} */
	
	/*@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	/*@Test
	@DisplayName("가격 내림차순 조회 테스트")
	public void findByPriceLessThanOrderByPriceDescTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}*/
	
	@Test
	@DisplayName("테스트 상품1 이고 Sell인 레코드")
	public void findByItemNmAndItemSellStatusTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격이 10004~10008 사이")
	public void findByPriceBetweenTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceBetween(10004,10008);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("regTime이 23-1-1 12:12:44이후")
	public void findByRegTimeAfterTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("itemSellStatus가 null이 아닌 레코드")
	public void findByItemSellStatusIsNotNullTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemSellStatusIsNotNull();
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("itemDetail이 설명1로 끝나는 레코드")
	public void findByItemDetailLikeTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailEndingWith("설명1");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
}
