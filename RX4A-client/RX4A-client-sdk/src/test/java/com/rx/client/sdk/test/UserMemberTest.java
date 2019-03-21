package com.rx.client.sdk.test;

import com.rx.client.sdk.UserMemberApiService;
import com.rx.client.sdk.SdkClient;
import com.rx.client.sdk.UserMgmtService;
import com.rx.client.test.IntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Category(IntegrationTest.class)
public class UserMemberTest {
  // private static OutStockApiService outStockApi = new OutStockApiService();
  private static final SdkClient sdkClient = SdkClient.getInstance();
  private static UserMemberApiService userMemberApiService =sdkClient.getUserMemberApiService();
  private static UserMgmtService ums=sdkClient.getUserMgmtService();
  private static String token = "";

  @BeforeClass
  public static void init() throws Exception{
    // checkApiService = sdkClient.get
//    initToken();
  }

  private static void initToken() throws Exception{
    if (token == null || token.equals("")) {
      String userName = "enterprise01";
      String password = "000000";
      token = ums.login(userName, password);
    }
  }

  // ------------------------------------------------------------
  @Test
  public void testShowUser() throws Exception{
    String resp = null;
    Map<String, Object> res = new HashMap<>();
    res = userMemberApiService.showUser("root");
    assertTrue("root".equals(res.get("id")));
  }


  @Test
  public void testSearchPersonList() throws Exception{ //
    List<Map<String, Object>> resp = null;
    resp = userMemberApiService.searchPersonList(1,10);
//    assertNotNull(resp);
    List<Map<String, Object>> list = resp;
    System.out.println(list.size());
    assertTrue(list.size() == 10);
  }


}
