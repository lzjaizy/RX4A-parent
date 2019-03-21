package com.rx.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.rx.client.RestApiResponse;
import com.rx.client.util.Json;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)
public class GsonTest {

  @Test
  public void mapObjectTest() {
    Map<String,String> map = new HashMap<>();
    map.put("key1", UUID.randomUUID().toString());
    map.put("key2", UUID.randomUUID().toString());
    Gson gson = new Gson();
    String jsonText = gson.toJson(map);

    assertNotNull(jsonText);
    assertTrue(jsonText.contains("key1"));
    assertTrue(jsonText.contains("key2"));

    Map<String,String> object = gson.fromJson(jsonText, HashMap.class);
    assertNotNull(object);
    assertEquals(map.toString(),object.toString());
  }

  @Test
  public void mapObjectTestWithGenericType() {
    Map<String,String> map = new HashMap<>();
    map.put("success", "true");
    map.put("message", UUID.randomUUID().toString());
    map.put("xyz",UUID.randomUUID().toString());
    map.put("data", UUID.randomUUID().toString());
    Gson gson = new Gson();
    String jsonText = gson.toJson(map);

    Type jsonType = new TypeToken<RestApiResponse<String>>() {}.getType();
    RestApiResponse<String> result = new Json().deserialize(jsonText, jsonType);
    assertNotNull(result);
    assertEquals(true,result.isSuccess());
    assertEquals(map.get("message"), result.getMessage());
    assertEquals(map.get("data"), result.getData());
  }

  @Test
  public void listGenericTypeTest() {
    Map<String,Object> map = new HashMap<>();
    map.put("success", "true");
    map.put("message", UUID.randomUUID().toString());
    map.put("xyz",UUID.randomUUID().toString());
    SimpleBean object = new SimpleBean(UUID.randomUUID().toString(),UUID.randomUUID().toString());
    List<SimpleBean> objects = new ArrayList<>();
    objects.add(object);
    map.put("data", objects);
    Gson gson = new Gson();
    String jsonText = gson.toJson(map);

    Type jsonType = new TypeToken<RestApiResponse<List<SimpleBean>>>() {}.getType();
    RestApiResponse<List<SimpleBean>> result = new Json().deserialize(jsonText, jsonType);
    assertNotNull(result);
    assertNotNull(result.getData());
  }

  class SimpleBean implements Serializable {
    private String key;
    private String value;

    public SimpleBean(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("SimpleBean{");
      sb.append("key='").append(key).append('\'');
      sb.append(", value='").append(value).append('\'');
      sb.append('}');
      return sb.toString();
    }
  }
}
