package com.rx.client.test;

import static org.junit.Assert.*;

import com.rx.client.util.HttpRequests;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)
public class HttpClientBasicTest {

  @Test
  public void fragmentTest() throws URISyntaxException {
    String baseUri = "http://localhost:8080";
    URIBuilder urib = new URIBuilder(baseUri);
    String path = "/gateway";
    assertEquals(baseUri,urib.build().toString());
    urib.setPath(path);
    assertEquals(baseUri + path, urib.build().toString());
  }

  @Test
  public void getRequestTest() {
    Map<String, String> values = new HashMap<>();
    values.put("username", UUID.randomUUID().toString());
    values.put("password", UUID.randomUUID().toString());
    HttpPost request = HttpRequests.newHttpPost("http://localhost:8080", values);
    assertNotNull(request);
  }
}
