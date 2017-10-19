package com.liuningfei.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHandleHelper {

	public static String getResponseJsonStr(String statusCode, String message, Object obj) {
		String finalJsonStr = "", dataStr = "";
		StringBuilder dataSB = new StringBuilder();
		if (obj instanceof List) {
			dataSB.append(JsonUtil.list2json((List<?>) obj));
        } else if (obj instanceof Map) {
        	  	dataSB.append(JsonUtil.map2json((Map<?, ?>) obj));
        } else {
        		dataSB.append("{}");
        }
		dataStr = dataSB.toString();
		Map<String, String> finalMap = new HashMap<String, String>(); 
		finalMap.put("statusCode", statusCode.length() > 0 ? statusCode : "1000");
		finalMap.put("message", message.length() > 0 ? message : "1000");
		finalMap.put("data", dataStr.length() > 0 ? dataStr : "{}");
		finalJsonStr = JsonUtil.map2json(finalMap);
		return finalJsonStr;
	}
}
