package com.myshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class ItemControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("상품 등록 페이지 권한 테스트")
	@WithMockUser(username = "admin", roles = "ADMIN") //유저가 로그인된 상태로 테스트 할 수 있게 해준다.
	public void itemFormTest() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) //get방식으로 리퀘스트를 한다.
	        .andDo(print()) //요청과 응답 메세지를 확인
	        .andExpect(status().isOk()); //응답 상태 코드가 정상인지 확인 후 정상이면 테스트 통과
	}
	
	@Test
	@DisplayName("상품 등록 페이지 일반 회원 접근 테스트")
	@WithMockUser(username = "user", roles = "USER") //유저가 로그인된 상태로 테스트 할 수 있게 해준다.
	public void itemFormNotAdminTest() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) //get방식으로 리퀘스트를 한다.
	        .andDo(print()) //요청과 응답 메세지를 확인
	        .andExpect(status().isForbidden()); //응답 상태 코드에서 Forbidden예외가 발생하면 테스트가 성공적으로 통과
	}

}
