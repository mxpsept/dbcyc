package com.kcm.common.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 系统服务获取工具类
 * (加注service为了让thymeleaf页面获取并使用springBean对象)
 *
 * @author shawn
 * @date 17/10/2018 10:38
 * @version 1.0
 */
@Service
public class EnvPropertyUtil {
	/**
	 * 注入spring系统环境服务
	 */
    private Environment env;
	@Autowired
	public EnvPropertyUtil(Environment env) {
		this.env = env;
	}

	/**
     * 从application.properties文件获取键值
	 *
     * @param key 变量key
     * @return 变量value
     */ 
    public String getAppProp(String key){
        return env.getProperty(key);
    }

//	/**
//	 * 获取当前登陆用户id
//	 * @return SysUser 当前用户
//	 */
//	public static String getCurrentUserId(){
//		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
//		return user.getUserId();
//	}

}
