package com.shop;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.repository.ItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ShopApplicationTests {

	@Autowired
	ItemRepository itemRepository;

	@Test
	@DisplayName("상품저장테스트")
	public void createItemTest(){

		Item item = new Item();

		item.setItemName("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트상품상세설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());

		Item savedItem = itemRepository.save(item);

		System.out.println(savedItem.toString());
	}


	public void createItemList(){
		for(int i=0; i<=10; i++){
			Item item = new Item();
			item.setItemName("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNameTest(){
		this.createItemList();;
		List<Item> itemList =itemRepository.findByItemName("테스트 상품1");
		for(Item item : itemList){
			System.out.println(item.toString());
		}
	}
}
