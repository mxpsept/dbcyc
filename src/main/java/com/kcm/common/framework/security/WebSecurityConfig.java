package com.kcm.common.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcm.common.core.domain.AjaxResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

/**
 * @Description: 安全框架配置文件
 * @Author: nie wei
 * @Date: 2020/8/18 15:19
 * @Version: 1.0
 */
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final IdentityCheckSuccessHandler successHandler;

    private final IdentityCheckFailureHandler failureHandler;

    private final CustomAuthenticationAccessDeniedHandler accessDeniedHandler;

    private final CustomAuthenticationEntryPoint entryPoint;

    private final CustomSessionInformationExpiredStrategy expiredStrategy;

    /**
     * 安全过滤器链配置函数
     *
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 会话信息过期策略
        http.sessionManagement().maximumSessions(1).expiredUrl("/login").expiredSessionStrategy(expiredStrategy);
        http.csrf().disable();
        http.authorizeRequests()
            // 如需跳过security权限，注释.anyRequest().authenticated()，将.anyRequest().permitAll()放行即可
            .anyRequest().authenticated()
            //.anyRequest().permitAll()
            .and()
            .formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login")
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .permitAll()
            .and()
            .logout()
            .logoutSuccessHandler((request, response, authentication) -> {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(AjaxResult.success()));
                out.flush();
                out.close();
            })
            .permitAll();
        http.exceptionHandling()
            // 处理已登录用户访问无权限资源
            .accessDeniedHandler(accessDeniedHandler)
            // 处理匿名用户访问资源
            .authenticationEntryPoint(entryPoint);
    }

    /**
     * 核心过滤器链配置函数
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) {
        //设置过滤忽略，将不会经过Spring Security过滤器链
        web.ignoring().antMatchers("/css/**","/js/**","/index","/webjars/**",
                "/swagger-resources/**","/v2/**","/swagger-ui.html");
    }

    /**
     * 认证管理器配置函数
     *
     * @param auth 认证管理构建器
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
