package com.example.esdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 基于fastjson封装的工具包
 * 
 */
public class FastJsonUtil {
	
	
	/**
	 * 将JSON格式的字符串转换成任意Java类型的对象
	 * 
	 * @param json
	 *            JSON格式的字符串
	 * @param type
	 *            任意Java类型
	 * @return 任意Java类型的对象
	 */
	public static <T> T toAny(String json, TypeReference<T> type) {
		return JSON.parseObject(json, type);
	}

	

	/**
	 * 把JSON文本parse为JSONObject或者JSONArray
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static Object toObject(String text) {

		Object object = null;

		if (!StringUtils.isEmpty(text)) {
			object = JSON.parse(text);
		}
		return object;
	}

	
	/**
	 * 把JSON文本parse成JSONObject
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static JSONObject toJSONObject(String text) {

		JSONObject jsonObject = null;

		if (!StringUtils.isEmpty(text)) {
			jsonObject = JSON.parseObject(text);
		}

		return jsonObject;
	}

	/**
	 * 将JSON格式的字符串转换为java类型的对象或者java数组类型的对象，不包括java集合类型
	 * 
	 * @param json
	 *            JSON格式的字符串
	 * @param clz
	 *            java类型或者java数组类型，不包括java集合类型
	 * @return java类型的对象或者java数组类型的对象，不包括java集合类型的对象
	 */
	public static <T> T toBean(String text, Class<T> clazz) {

		T t = null;

		if (!StringUtils.isEmpty(text)) {
			t = JSON.parseObject(text, clazz);
		}

		return t;
	}

	/**
	 * 把JSON文本parse成JSONArray
	 * 
	 * @param text
	 * 
	 * @return
	 */
	public static JSONArray toJSONArray(String text) {

		JSONArray jsonArray = null;

		if (!StringUtils.isEmpty(text)) {
			jsonArray = JSON.parseArray(text);
		}
		return jsonArray;
	}

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 * @param object
	 * 
	 * @return
	 */
	public static String toJSONString(Object object) {

		String jsonString = "";

		if (object != null) {
			jsonString = JSON.toJSONString(object);
		}

		return jsonString;
	}

	/**
	 * 将JavaBean序列化为带格式的JSON文本
	 * 
	 * @param object
	 * 
	 * @param prettyFormat
	 * 
	 * @return
	 */
	public static String toJSONString(Object object, boolean prettyFormat) {

		String jsonString = "";

		if (object != null) {
			jsonString = JSON.toJSONString(object, prettyFormat);
		}

		return jsonString;
	}

	/**
	 * 将JavaBean转换为JSONObject或者JSONArray
	 * 
	 * @param javaObject
	 * 
	 * @return
	 */
	public static Object toJSON(Object javaObject) {

		Object json = null;

		if (javaObject != null) {
			json = JSON.toJSON(javaObject);
		}

		return json;
	}

	/**
	 * 将Json文本转成HashMap
	 * 
	 * @param text
	 * 
	 * @return
	 */
	public static Map<String, Object> toHashMap(String text) {

		HashMap<String, Object> map = new HashMap<>();

		JSONObject jsonObject = toJSONObject(text);
		Set<String> set = jsonObject.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 将JSON格式的字符串转换为List<T>类型的对象
	 * 
	 * @param text
	 *            JSON格式的字符串
	 * @param clz
	 *            指定泛型集合里面的T类型
	 * @return List<T>类型的对象
	 */
	public static <T> List<T> toList(String text, Class<T> clazz) {

		List<T> list = null;

		if (!StringUtils.isEmpty(text)) {
			list = JSON.parseArray(text, clazz);
		}
		return list;
	}

}
