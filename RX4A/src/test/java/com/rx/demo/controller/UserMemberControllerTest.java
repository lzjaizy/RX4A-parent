package com.rx.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMemberControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;
  private MockHttpSession session;

  @Before
  public void setupMockMvc(){
    mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    session = new MockHttpSession();
  }

  @Test
  public void toIndexTest() throws Exception{
    mvc.perform(MockMvcRequestBuilders.get("/user/showUser?id=root")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .session(session)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())//测试返回接口内容
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("root"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("1"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void searchPersonListTest() throws Exception{
//    String json="{\"page\":\"1\",\"rows\":\"10\"}";
    mvc.perform(MockMvcRequestBuilders.post("/user/searchPersonList")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .param("page","1")//传json参数
        .param("rows","10")//传json参数
        .session(session)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}