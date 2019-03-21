package com.rx.client.sdk;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * comm Api.
 */
public interface CommApiService {


  /**
   * 新增和更新数据.
   *
   * @param token token
   * @return transactionId
   */
  Map invoke(String token, List<String> list, Map<String, String> map) throws IOException;

  /**
   * 删除出库明细数据.
   *
   * @param token token
   * @param id
   * @return transactionId
   */
  String delete(String token, String id) throws IOException;

  /**
   * 根据 ID 查询出库明细历史数据.
   *
   * @param token token
   * @param id 入场明细数据ID
   * @param flag 是否解析加密数据标示
   *
   * @return 入场明细历史数据集合
   */
  List<String> findHistoryById(String token, String id, boolean flag, Map<String, String> map) throws IOException;
}
