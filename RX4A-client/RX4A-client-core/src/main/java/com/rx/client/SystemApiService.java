package com.rx.client;

import java.io.IOException;

/**
 * 系统 Api.
 *
 * @author yang.tiedang@cesgroup.com.cn
 *
 */
public interface SystemApiService extends ApiService {

  /**
   * Echo service.
   *
   * @param message
   * @return
   */
  RestApiResponse<String> echo(String message) throws IOException;

}
