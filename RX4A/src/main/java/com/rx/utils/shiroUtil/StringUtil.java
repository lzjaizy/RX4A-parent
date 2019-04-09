package com.rx.utils.shiroUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
  protected static Log log = LogFactory.getLog(StringUtil.class);
  public static final String RETURN_AND_NEWLINE = "RETURN_AND_NEWLINE";

  public StringUtil() {
  }

  public static boolean isNotBlank(String srcStr) {
    return srcStr != null && srcStr.trim().length() > 0;
  }

  public static boolean isBlank(String srcStr) {
    return srcStr == null || srcStr.trim().length() == 0;
  }

  public static String highHeader(String srcStr) {
    String tempStr = srcStr;
    if (srcStr != null && srcStr.length() > 0) {
      tempStr = srcStr.toLowerCase();
      tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1);
    }

    return tempStr;
  }

  public static String lowerHeader(String srcStr) {
    String tempStr = srcStr;
    if (srcStr != null && srcStr.length() > 0) {
      tempStr = srcStr.substring(0, 1).toLowerCase() + srcStr.substring(1);
    }

    return tempStr;
  }

  public static String dbnameToFieldName(String srcStr) {
    if (srcStr != null) {
      String[] strs = slice(srcStr, "_");
      if (strs.length == 1) {
        if (srcStr.toUpperCase().equals(srcStr)) {
          srcStr = srcStr.toLowerCase();
        }

        return lowerHeader(srcStr);
      } else {
        StringBuffer strBuf = new StringBuffer();

        for(int i = 0; i < strs.length; ++i) {
          if (i == 0) {
            strBuf.append(strs[i].toLowerCase());
          } else {
            strBuf.append(highHeader(strs[i]));
          }
        }

        return strBuf.toString();
      }
    } else {
      return null;
    }
  }

  public static String[] slice(String srcString, String key) {
    List strList = new ArrayList();
    if (srcString != null) {
      if (srcString != null && key == null) {
        log.debug("key:" + key);
      } else {
        String tempStr = srcString;

        for(int index = srcString.indexOf(key); index >= 0; index = tempStr.indexOf(key)) {
          strList.add(tempStr.substring(0, index));
          tempStr = tempStr.substring(index + key.length());
        }

        if (tempStr != null) {
          strList.add(tempStr);
        }
      }
    }

    return (String[])strList.toArray(new String[0]);
  }

  public static String[] slice(String srcString, String key, boolean noBlank) {
    List strList = new ArrayList();
    if (srcString == null) {
      log.debug("srcString:" + srcString);
    } else if (srcString != null && key == null) {
      log.debug("srcString:" + srcString);
      log.debug("key:" + key);
    } else {
      String tempStr = srcString;

      for(int index = srcString.indexOf(key); index >= 0; index = tempStr.indexOf(key)) {
        String subStr = tempStr.substring(0, index);
        if (noBlank) {
          if (isNotBlank(subStr)) {
            strList.add(subStr);
          }
        } else {
          strList.add(subStr);
        }

        tempStr = tempStr.substring(index + key.length());
      }

      if (tempStr != null) {
        strList.add(tempStr);
      }
    }

    return (String[])strList.toArray(new String[0]);
  }

  public static String dbnameToClassName(String srcStr) {
    if (srcStr == null) {
      return srcStr;
    } else {
      String[] strs = slice(srcStr, "_");
      if (strs.length == 1) {
        if (srcStr.toUpperCase().equals(srcStr)) {
          srcStr = highHeader(srcStr);
        }

        return srcStr;
      } else {
        StringBuffer strBuf = new StringBuffer();

        for(int i = 0; i < strs.length; ++i) {
          strBuf.append(highHeader(strs[i]));
        }

        return strBuf.toString();
      }
    }
  }

  public static String replaceString(String mainString, String oldString, String newString, boolean ignoreCase) {
    if (mainString == null) {
      return null;
    } else if (oldString != null && oldString.length() != 0) {
      if (newString == null) {
        newString = "";
      }

      String newMainString = mainString;
      if (ignoreCase) {
        newMainString = mainString.toUpperCase();
      }

      int i = newMainString.lastIndexOf(oldString);
      if (i < 0) {
        return mainString;
      } else {
        StringBuffer mainSb;
        for(mainSb = new StringBuffer(mainString); i >= 0; i = newMainString.lastIndexOf(oldString, i - 1)) {
          mainSb.replace(i, i + oldString.length(), newString);
        }

        return mainSb.toString();
      }
    } else {
      return mainString;
    }
  }

  public static List split(String str, String delim) {
    List splitList = null;
    StringTokenizer st = null;
    if (str == null) {
      return splitList;
    } else {
      if (delim != null) {
        st = new StringTokenizer(str, delim);
      } else {
        st = new StringTokenizer(str);
      }

      if (st != null && st.hasMoreTokens()) {
        splitList = new ArrayList();

        while(st.hasMoreTokens()) {
          splitList.add(st.nextToken());
        }
      }

      return splitList;
    }
  }

  public static String appendZero(String str, int count) {
    return append(str, count, "0");
  }

  public static String append(String str, int count, String appendStr) {
    if (str == null) {
      return str;
    } else {
      StringBuffer appendBuff = new StringBuffer(str);

      for(int i = str.length(); i < count; ++i) {
        appendBuff.insert(0, appendStr);
      }

      return appendBuff.toString();
    }
  }

  public static String replaceAll(String source, String finder, String replacement) {
    if (source != null && !source.equals("")) {
      if (finder == null || finder.equals("")) {
        finder = " ";
      }

      String str0 = source;
      String target = "";

      try {
        if (finder == "RETURN_AND_NEWLINE") {
          while(str0.indexOf("\r") != -1) {
            target = target + str0.substring(0, str0.indexOf("\r")) + replacement;
            str0 = str0.substring(str0.indexOf("\r") + 2);
          }
        } else {
          while(str0.indexOf(finder) != -1) {
            target = target + str0.substring(0, str0.indexOf(finder)) + replacement;
            str0 = str0.substring(str0.indexOf(finder) + finder.length());
          }
        }

        target = target + str0;
      } catch (Exception var6) {
        var6.printStackTrace();
      }

      if (target.equals("")) {
        target = str0;
      }

      return target;
    } else {
      return source;
    }
  }
}
