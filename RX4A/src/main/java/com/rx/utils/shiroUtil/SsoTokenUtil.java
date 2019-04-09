package com.rx.utils.shiroUtil;


import com.rx.shiro.entity.SsoToken;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SsoTokenUtil {
  static Log log = LogFactory.getLog(SsoTokenUtil.class);
  public static DateUtils du = new DateUtils();
  private static final String formatStrTime = "yyyy-MM-ddHHmmss";
  public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
  public static final String TOKEN_STEP = "$$";

  public SsoTokenUtil() {
  }

  public static String encodeToken(SsoToken ssoToken) {
    StringBuffer tokenBuff = new StringBuffer();
    tokenBuff.append(ssoToken.getTokenId());
    tokenBuff.append("$$").append(ssoToken.getLoginName());
    tokenBuff.append("$$").append(ssoToken.getUserName());
    tokenBuff.append("$$").append(DateFormatUtils.format(ssoToken.getExpireDate(), "yyyy-MM-ddHHmmss"));
    return tokenBuff.toString();
  }

  public static SsoToken decodeToken(String ssoTokenStr)  {
    String[] tokenParts = StringUtil.slice(ssoTokenStr, "$$");
    if (tokenParts.length == 4) {
      SsoToken ssoToken = new SsoToken();
      String tokenId = tokenParts[0];
      ssoToken.setTokenId(tokenId);
      ssoToken.setLoginName(tokenParts[1]);
      ssoToken.setUserName(tokenParts[2]);

      try {
        ssoToken.setExpireDate(DateUtils.parseDate(tokenParts[3], new String[]{"yyyy-MM-ddHHmmss"}));
        Date nowDate = new Date();
        if (nowDate.getTime() > ssoToken.getExpireDate().getTime()) {
          ssoToken.setValidToken(false);
        } else {
          ssoToken.setValidToken(true);
        }
      } catch (ParseException var5) {
        log.error(var5.getMessage() + ",日期格式发生异常:" + tokenParts[3] + ",令牌=" + ssoTokenStr);
        log.debug(var5.getMessage() + ",日期格式发生异常:" + tokenParts[3] + ",令牌=" + ssoTokenStr);
        log.info(var5.getMessage() + ",日期格式发生异常:" + tokenParts[3] + ",令牌=" + ssoTokenStr);
//        CommonSystemOut.SystemOutFun("发生格式化错误的日期字符串：" + tokenParts[3]);
//        CommonSystemOut.SystemOutFun("发生格式化错误的令牌字符串：" + ssoTokenStr);
        var5.printStackTrace();
      }

      return ssoToken;
    } else {
      return null;
    }
  }

//  public static void main(String[] args) throws SsoFacadeException {
//    SsoToken ssoToken = new SsoToken();
//    ssoToken.setTokenId("111");
//    ssoToken.setLoginName("superadmin");
//    ssoToken.setUserName("超级管理员");
//    ssoToken.setExpireDate(new Date((new Date()).getTime() + 3600000L));
//    String token = encodeToken(ssoToken);
//    System.out.println("令牌字符串：" + token);
//    String encodeToken = new String(Base64.encode(token.getBytes()));
//    System.out.println("加密后的令牌：" + encodeToken);
//    String decodeToken = new String(Base64.decode(encodeToken));
//    SsoToken ssoToken1 = decodeToken(decodeToken);
//    System.out.println("用户登录名：" + ssoToken1.getLoginName());
//    System.out.println("令牌过期时间：" + sdf.format(ssoToken1.getExpireDate()));
//  }
}
