//package com.cesgroup.xfc.client.sdk.test;
//
//import com.cesgroup.xfc.client.*;
//import com.cesgroup.xfc.client.sdk.internal.UserMgmtServiceImpl;
//import com.cesgroup.xfc.client.test.IntegrationTest;
//import com.cesgroup.xfc.client.util.Constants;
//import com.cesgroup.xfc.client.util.HttpRequests;
//import com.cesgroup.xfc.client.sdk.SdkClient;
//import com.cesgroup.xfc.client.sdk.UserMgmtService;
//import java.io.IOException;
//import java.util.*;
//
//import com.cesgroup.xfc.common.model.RoleCode;
//import com.cesgroup.xfc.common.model.UserInfo;
//import com.google.gson.reflect.TypeToken;
//import org.apache.http.Header;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.protocol.HttpContext;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//
//import static org.junit.Assert.*;
//
//@Category(IntegrationTest.class)
//public class UserManagementTest {
//  private static SdkClient sdkClient = SdkClient.getInstance();
//  private static UserMgmtService ums;
//
//  @BeforeClass
//  public static void init() {
//    ums = sdkClient.getUserMgmtService();
//  }
//
//  @Test
//  //@Ignore
//  public void loginTest() throws Exception {
//    String userName = "org4admin";
//    String password = "adminpw";
//    String token = ums.login(userName, password);
//    assertNotNull(token);
//  }
//
//  @Test
//  //@Ignore
//  public void getUserIdTest() throws Exception {
//    String userName = "enterprise01";
//    String password = "000000";
//    String userId = ums.getUserId(userName, password);
//    assertNotNull(userId);
//  }
//
//  @Test(expected = ServiceException.class)
//  public void loginWithErrorPassword() throws Exception {
//    String userName = "enterprise01";
//    String password = "xyz";
//    String token = ums.login(userName, password);
//    assertNotNull(token);
//  }
//
//  @Test(expected = ServiceException.class)
//  public void loginWithUnknownUser() throws Exception {
//    String userName = "enterpris1";
//    String password = "000000";
//    String token = ums.login(userName, password);
//    assertNotNull(token);
//  }
//
//  @Test
//  public void multiSessionTest002() throws Exception {
//    String userName1 = "enterprise01";
//    String userName2 = "enterprise02";
//    String password = "000000";
//
//    String token1 = ums.login(userName1, password);
//    assertNotNull(token1);
//    assertNotNull(((UserMgmtServiceImpl)ums).getAppContext().getHttpContext(token1));
//
//    String token2 = ums.login(userName2, password);
//    assertNotNull(token2);
//    assertNotEquals("",token1,token2);
//    assertNotNull(((UserMgmtServiceImpl)ums).getAppContext().getHttpContext(token2));
//  }
//
//  @Test
//  public void multiSessionTest() throws Exception {
//    String userName1 = "enterprise01";
//    String userName2 = "enterprise02";
//    String password = "000000";
//
//    HttpPost loginPost1 = newLoginPost(userName1,password);
//    HttpClientContext context = new HttpClientContext();
//    CookieStore cookieStore = new BasicCookieStore();
//    context.setCookieStore(cookieStore);
//    ApiResponse<String> response = sdkClient.getHttpClient().execute(loginPost1,
//        new ApiResponseHandler<String>() {}, context);
//    assertNotNull(response);
//    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
//    String token = response.getData();
//    assertNotNull(token);
//    assertTrue(cookieStore.getCookies().size() > 0);
//    Cookie cookie1 = cookieStore.getCookies().get(0);
//    String sid1 = cookie1.getValue();
//    assertNotNull(sid1);
//
//    HttpPost loginPost2 = newLoginPost(userName2,password);
//    HttpClientContext context2 = HttpClientContext.create();
//    CookieStore cookieStore2 = new BasicCookieStore();
//    context2.setCookieStore(cookieStore2);
//    ApiResponse<String> response2 = sdkClient.getHttpClient().execute(loginPost2,
//        new ApiResponseHandler<String>() {}, context2);
//    assertNotNull(response2);
//    assertEquals(HttpStatus.SC_OK, response2.getStatusCode());
//    String token2 = response2.getData();
//    assertNotNull(token2);
//    assertTrue(cookieStore2.getCookies().size() > 0);
//    Cookie cookie2 = cookieStore2.getCookies().get(0);
//    String sid2 = cookie2.getValue();
//    assertNotNull(sid2);
//
//    assertTrue(token != token2);
//    assertTrue(sid1 != sid2);
//
//    RestApiResponse<String> echoResp = doEcho("hello", context);
//    HttpRequest echoReq = context.getRequest();
//    Header cookieHeader = echoReq.getHeaders(Constants.HEADER_COOKIE)[0];
//    assertNotNull(cookieHeader);
//    assertEquals("sid=" + sid1, cookieHeader.getValue());
//  }
//
//  //@Ignore
//  @Test
//  public void testLogout() throws Exception {
//    String userName = "enterprise01";
//    String password = "000000";
//    String token = ums.login(userName, password);
//
//    ums.logout(token);
//    assertTrue(true);
//  }
//
//  private HttpPost newLoginPost(String userName, String password) throws IOException {
//    Map<String, String> pairs = new HashMap<>();
//    pairs.put(Constants.FORM_USER_NAME, userName);
//    pairs.put(Constants.FORM_PASSWORD, password);
//    pairs.put(Constants.FORM_VERFICATION, 1 + "");
//    HttpPost post = HttpRequests.newHttpPost(ums.getConfiguration(Constants.PROP_GATEWAY_SERVER_URL) + Constants.URL_LOGIN, pairs);
//    post.addHeader(Constants.FORM_TENANTID, ums.getConfiguration(Constants.PROP_TENANT_ID));
//    return post;
//  }
//
//  private RestApiResponse<String> doEcho(String message, HttpContext context) throws Exception {
//    Map<String, String> pairs = new HashMap<>();
//    pairs.put(Constants.MESSAGE, message);
//    HttpGet hget = HttpRequests.newHttpGet(ums.getConfiguration(Constants.PROP_GATEWAY_SERVER_URL) + Constants.URL_ECHO,pairs);
//    RestApiResponse<String> response = sdkClient.getHttpClient().execute(hget,
//        new RestApiResponseHandler<>(new TypeToken<RestApiResponse<String>>() {}),context);
//    return response;
//  }
//
//  @Ignore
//  @Test
//  public void registerTest() throws Exception {
//    // do login with admin user first.
//    //String adminUser = "admin";
//    //String adminSecret = "adminpw";
//    String adminUser = "admin01";
//    String adminSecret = "CEScbaas666";
//
//    String token = ums.login(adminUser,adminSecret);
//    assertNotNull(token);
//
//    String orgMspId = "Org1MSP";
//    String userId = UUID.randomUUID().toString();
//    String secret = UUID.randomUUID().toString();
//    String roleCode = RoleCode.XFC_ENTERPRISE;
//    UserInfo userInfo = genUserInfo(userId,secret,orgMspId,roleCode);
//
//    UserInfo resp = ums.register(token, userInfo);
//    assertNotNull(resp);
//    assertEquals(orgMspId, resp.getOrgMspId());
//    assertNotNull(resp.getEnrollmentInfo());
//    Thread.sleep(60000);
//
//    // try login using new user.
//    ums.logout(token);
//    String token2 = ums.login(userId,secret);
//    assertNotNull(token2);
//  }
//
//  @Ignore
//  @Test
//  public void registerTestWithMultiCurrentUsers() throws Exception {
//    // do login with admin user first.
//    String adminUser = "org1Admin";
//    String adminSecret = "adminpw";
//    String token = ums.login(adminUser,adminSecret);
//    assertNotNull(token);
//
//    String orgMspId = "Org1MSP";
//    String roleCode = RoleCode.XFC_ENTERPRISE;
//    String secret = "000000";
//    List<UserInfo> users = new ArrayList<>();
//    int size = 10;
//    for (int i = 0; i < size; i++) {
//      UserInfo userInfo = genUserInfo(UUID.randomUUID().toString(),secret, orgMspId, roleCode);
//      UserInfo createdUser = ums.register(token, userInfo);
//      assertNotNull(createdUser);
//      assertNotNull(createdUser.getEnrollmentInfo());
//      users.add(createdUser);
//    }
//    Thread.sleep(60000);
//
//    // do check.
//    ums.logout(token);
//    assertEquals(size, users.size());
//    for (UserInfo user : users) {
//      String token2 = ums.login(user.getUserName(),user.getSecret());
//      assertNotNull(token2);
//      ums.logout(token2);
//      Thread.sleep(5000);
//    }
//  }
//
//  @Test (expected=IOException.class)
//  public void registerTestWithWrongOrgMspId() throws Exception {
//    // do login with admin user first.
//    String token = null;
//    try {
//      String adminUser = "org4admin";
//      String adminSecret = "adminpw";
//      token = ums.login(adminUser,adminSecret);
//      assertNotNull(token);
//    } catch (Exception e) {
//      fail(e.getMessage());
//    }
//
//    String orgMspId = "Org1MSP";
//    String userId = UUID.randomUUID().toString();
//    String secret = UUID.randomUUID().toString();
//    String roleCode = RoleCode.XFC_ENTERPRISE;
//    UserInfo userInfo = genUserInfo(userId,secret,orgMspId,roleCode);
//
//    UserInfo resp = ums.register(token, userInfo);
//    assertNotNull(resp);
//  }
//
//  @Test (expected=IOException.class)
//  public void registerTestWithNonAlliansAdminRole() throws Exception {
//    // do login with admin user first.
//    String token = null;
//    try {
//      String adminUser = "enterprise01";
//      String adminSecret = "000000";
//      token = ums.login(adminUser,adminSecret);
//      assertNotNull(token);
//    } catch (Exception e) {
//      fail(e.getMessage());
//    }
//
//    String orgMspId = "Org1MSP";
//    String userId = UUID.randomUUID().toString();
//    String secret = UUID.randomUUID().toString();
//    String roleCode = RoleCode.XFC_ENTERPRISE;
//    UserInfo userInfo = genUserInfo(userId,secret,orgMspId,roleCode);
//
//    UserInfo resp = ums.register(token, userInfo);
//    assertNotNull(resp);
//  }
//
////  @Test
////  @Ignore
////  public void userPwdTest() throws Exception {
////    /*新建用户修改新用户的密码*/
////    Map<String,String> params = new HashMap<>();
////    /*生成账户名称*/
////    String userName ="TEST-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
////    params.put("orgMspId","Org1MSP");
////    //用户名
////    params.put("userId",userName);
////    //用户密码
////    params.put("secret","000000");
////    //角色名
////    params.put("role","消费者");
////    //角色id
////    params.put("roleId","a2ced99187c54bf4ab8f6193f59f3bcc");
////    //租户id
////    params.put("tenantId","9f2a19299c514c8db0c9014bdb8ac4b6");
////    //管理员账号
////    params.put("adminUsername","cesbaas");
////    //管理员密码
////    params.put("adminPassword","000000");
////    ApiResponse resp1 = ums.register(params);
////    assertNotNull(resp1);
////    /*开始修改*/
////    ApiResponse<Map<String,String>> resp = null;
////    String loginPassword = "000000";
////    String token = ums.login(userName, loginPassword);
////    assertNotNull(token);
////    resp = ums.userPwd(token,"000001");
////    assertNotNull(resp);
////    Map<String, String> data = resp.getData();
////    assertEquals("200",data.get("code"));
////    assertEquals(HttpStatus.SC_OK, resp.getStatusCode());
////    /*检查使用旧密码能否登陆*/
////    ApiResponse<Map<String,String>> resp2 = null;
//////    String loginPassword2 = "000000";
//////    String token2 = ums.login(userName, loginPassword2);
//////    assertNotNull(token2);
////    /*重新将密码改回来*/
////    resp2 = ums.userPwd(token,"000000");
////    Map<String, String> data2 = resp2.getData();
////    assertNotNull(resp2);
////    assertEquals("200",data2.get("code"));
////    assertEquals(HttpStatus.SC_OK, resp2.getStatusCode());
////  }
//
//  @Ignore
//  @Test
//  public void changePasswordByUsernameTest() throws Exception {
//    /*新建用户修改新用户的密码*/
//    /*开始修改*/
//    ApiResponse<Map<String,String>> resp = null;
//    resp = ums.changePasswordByUsername("TEST-20180927094229","000000","000001");
//    assertNotNull(resp);
//    Map<String, String> data = resp.getData();
//    assertEquals("200",data.get("code"));
//    assertEquals(HttpStatus.SC_OK, resp.getStatusCode());
//    /*检查使用旧密码能否登陆*/
//    ApiResponse<Map<String,String>> resp2 = null;
//    String loginPassword2 = "000000";
//    boolean success = false;
//    try{
//      String token2 = ums.login("TEST-20180927094229", loginPassword2);
//    }catch(Exception e){
//      success = true;
//    }
//    assertTrue(success);
//    /*重新将密码改回来*/
//    resp2 = ums.changePasswordByUsername("TEST-20180927094229","000001","000000");
//    Map<String, String> data2 = resp2.getData();
//    assertNotNull(resp2);
//    assertEquals("200",data2.get("code"));
//    assertEquals(HttpStatus.SC_OK, resp2.getStatusCode());
//  }
//
//  private UserInfo genUserInfo(String userName, String secret, String orgMspId, String roleCode) {
//    UserInfo userInfo = new UserInfo();
//    userInfo.setUserName(userName);
//    userInfo.setOrgMspId(orgMspId);
//    userInfo.setRoleCode(roleCode);
//    userInfo.setSecret(secret);
//    userInfo.setTenantId("9f2a19299c514c8db0c9014bdb8ac4b6");
//    return userInfo;
//  }
//}
