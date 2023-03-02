package com.jk.mock.core.code.util;

import com.alibaba.fastjson2.JSONObject;
import com.jk.mock.entity.MockInfo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

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
    private static final ThreadLocal<String> currentUrlGroup = new ThreadLocal<String>();

    public static String getUrlInterface() {
        if (StringUtils.isNotBlank(currentUrlInterface.get())) {
            return currentUrlInterface.get();
        }
        return "";
    }

    public static String getUrlGroup() {
        if (StringUtils.isNotBlank(currentUrlGroup.get())) {
            return currentUrlGroup.get();
        }
        return "";
    }

    public static void setUrlInterface(String currentUrlInterfaceName) {
        currentUrlInterface.set(currentUrlInterfaceName);
    }

    public static void setUrlUrlGroup(String currentUrlGroupName) {
        currentUrlGroup.set(currentUrlGroupName);
    }

    public static void clear(){
        currentUrlInterface.set(null);
        currentUrlGroup.set(null);
    }

    public static JSONObject canaryReqHandle(@NotNull MockInfo mockInfo){
        JSONObject resp = JSONObject.parseObject(mockInfo.getResponse());
        JSONObject entityModel = resp.getJSONObject("data");
        Map<Integer,AttributeModel> attributeListMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : entityModel.getJSONObject("attributeList").entrySet()) {
            Integer canaryKey  = Integer.valueOf(entry.getKey());
            AttributeModel attributeModel = JSONObject.parseObject(entry.getValue().toString(),AttributeModel.class);
            attributeListMap.put(canaryKey,attributeModel);
        }
        entityModel.put("attributeList",attributeListMap);
        resp.put("data",entityModel);
        return resp;
    }
}
