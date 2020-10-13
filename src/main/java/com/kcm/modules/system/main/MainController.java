package com.kcm.modules.system.main;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 系统登录配置
 * @Author: nie wei
 * @Date: 2020/8/18 15:19
 * @Version: 1.0
 */
@RestController
public class MainController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @PreAuthorize("hasAuthority('sys:user:and')")
    @RequestMapping("/home")
    public String index(){
        return "进入主页";
    }

    /**
     * 没有登录时返回前台的信息
     * @return 未登录
     */
    @RequestMapping("/unLogin")
    public Object unLogin(){
        Map<String, Object> result = new HashMap<>(2);
        result.put("message","未登录，请您先登录");
        return result;
    }

    /**
     * 登录后没有权限时返回前台的信息
     * @return 无权限
     */
    @RequestMapping("/url403")
    public Object url403(){
        Map<String, Object> result = new HashMap<>(2);
        result.put("message","未登录403，请您先登录");
        return result;
    }

    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
