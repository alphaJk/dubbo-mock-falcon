package com.jk.mock.core.code;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/22
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Util {
    private static final ThreadLocal<String> currentUrlInterface = new ThreadLocal<String>();

    public static String getUrlInterface() {
        if (StringUtils.isNotBlank(currentUrlInterface.get())) {
            return currentUrlInterface.get();
        }
        return "";
    }

    public static void setUrlInterface(String currentUrlInterfaceName) {
        currentUrlInterface.set(currentUrlInterfaceName);
    }

    public static void clear(){
        currentUrlInterface.set(null);
    }
}
