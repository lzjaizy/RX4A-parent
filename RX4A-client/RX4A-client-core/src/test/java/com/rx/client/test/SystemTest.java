package com.rx.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.rx.client.ApiResponse;
import com.rx.client.ApiResponseHandler;
import com.rx.client.AppClient;
import com.rx.client.RestApiResponseHandler;
import com.rx.client.internal.SystemApiServiceImpl;
import com.rx.client.util.Constants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.reflect.TypeToken;
import com.rx.client.RestApiResponse;
import com.rx.client.SystemApiService;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class SystemTest {
  private static final AppClient appClient = AppClient.getInstance();

  @Test
  public void echoTest() throws Exception {
    String message = "hello";
    SystemApiService systemApi = appClient.getSystemApiService();
    RestApiResponse<String> resp = systemApi.echo(message);
    assertNotNull(resp);
    assertEquals(message, resp.getMessage());
    assertTrue(resp.isSuccess());
  }

  @Test(expected = IllegalArgumentException.class)
  public void echoClientServiceTestWithLargeMessage() throws Exception {
    StringBuilder sb = new StringBuilder();
    for (;;) {
      sb.append(UUID.randomUUID().toString());
      if (sb.length() > 1024) {
        break;
      }
    }

    SystemApiService systemApi = appClient.getSystemApiService();
    RestApiResponse<String> resp = systemApi.echo(sb.toString());
    assertNotNull(resp);
  }

  //@Test(expected = IOException.class)
  @Test
  public void echoServerServiceTestWithLargeMessage() throws Exception {
    StringBuilder sb = new StringBuilder();
    for (;;) {
      sb.append(UUID.randomUUID().toString());
      if (sb.length() > 1024) {
        break;
      }
    }

    Map<String,String> params = new HashMap<>();
    params.put(Constants.MESSAGE, sb.toString());
    SystemApiServiceImpl apiService = (SystemApiServiceImpl)appClient.getSystemApiService();
    HttpGet hg = HttpRequests.newHttpGet( apiService.getConfiguration(Constants.PROP_GATEWAY_SERVER_URL) + "/echo", params);
    RestApiResponse<String> resp = appClient.getHttpClient().execute(hg,
        new RestApiResponseHandler<>(new TypeToken<RestApiResponse<String>>() {}));
    assertNotNull(resp);
    assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,resp.getStatusCode());
    assertTrue(!Strings.isEmpty(resp.getMessage()));
  }

  @Test(expected= Exception.class)
  public void Error404Test() throws Exception {
    SystemApiServiceImpl apiService = (SystemApiServiceImpl)appClient.getSystemApiService();
    HttpGet hg = HttpRequests.newHttpGet( apiService.getConfiguration(Constants.PROP_GATEWAY_SERVER_URL) + "/test/abcde", null);
    ApiResponse<String> resp = appClient.getHttpClient().execute(hg, new ApiResponseHandler<String>() {});
  }
}
