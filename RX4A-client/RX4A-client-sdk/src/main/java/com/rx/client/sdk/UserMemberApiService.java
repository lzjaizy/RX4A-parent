package com.rx.client.sdk;

import com.rx.client.ApiService;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserMemberApiService extends ApiService {
  /**
   * 新增或更新用户证书信息。
   *
   * @return 成功
   */
  Map<String, Object> showUser(String id) throws IOException;

  /**
   *
   * @return
   * @throws IOException
   */
  List<Map<String, Object>> searchPersonList(int page ,int rows) throws IOException;

}
