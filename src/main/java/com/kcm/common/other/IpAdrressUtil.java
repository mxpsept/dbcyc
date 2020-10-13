package com.kcm.common.other;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpAdrressUtil {
	/**
	 * 获取Ip地址
	 * @param request 请求
	 * @return 返回ip
	 */
	public static String getIpAdrress(HttpServletRequest request) {
		String xIp = request.getHeader("X-Real-IP");
		String xFor = request.getHeader("X-Forwarded-For");
		String string = "unknown";
		if(StringUtils.isNotEmpty(xFor) && !string.equalsIgnoreCase(xFor)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = xFor.indexOf(",");
			if(index != -1){
				return xFor.substring(0,index);
			}else{
				return xFor;
			}
		}
		xFor = xIp;
		if(StringUtils.isNotEmpty(xFor) && !string.equalsIgnoreCase(xFor)){
			return xFor;
		}
		if (StringUtils.isBlank(xFor) || string.equalsIgnoreCase(xFor)) {
			xFor = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(xFor) || string.equalsIgnoreCase(xFor)) {
			xFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(xFor) || string.equalsIgnoreCase(xFor)) {
			xFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(xFor) || string.equalsIgnoreCase(xFor)) {
			xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(xFor) || string.equalsIgnoreCase(xFor)) {
			xFor = request.getRemoteAddr();
		}
		return xFor;
	}
}