/**
 * 
 */
package com.wxapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应实体工具类
 * 
 * @author 马鑫
 * @datetime 2020年1月13日 下午7:08:44
 */
public class Result extends HashMap<String, Object>{

	private static final long serialVersionUID = 951143295963369585L;
	public Result() {
		put("code", 0);
		put("msg", true);
	}

	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}
	
	public static Result ok(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static Result ok(int code, String msg, Map<String, Object> data) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data", data);
		return r;
	}

	public static Result ok() {
		return new Result();
	}

	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public static Result error() {
		return error(-1, "未知异常，请联系管理员");
	}
	
	public static Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
}
