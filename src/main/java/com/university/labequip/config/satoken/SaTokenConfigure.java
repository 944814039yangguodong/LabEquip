package com.university.labequip.config.satoken;


import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;


/**
 * [Sa-Token 权限认证] 配置类
 *
 * @author Guodong
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册sa-token的拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册注解拦截器
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**").excludePathPatterns("");

        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {

            // 登录验证 -- 排除多个路径
            SaRouterUtil.match(Collections.singletonList("/**"), Arrays.asList("/api/user/login", "/doc.html", "/webjars/**", "/img.icons/**", "/swagger-resources/**", "/v2/api-docs",
                    "/swagger**/**",
                    "/webjars/**",
                    "/v3/**",
                    "/doc.html"
            ), StpUtil::checkLogin);

//            // 角色认证 -- 拦截student的路由
//            SaRouterUtil.match(Arrays.asList("/api/student/**", "/api/project/student/**"), () -> StpUtil.checkRoleOr("STUDENT"));
//
//            // 角色认证 -- 拦截teacher的路由
//            SaRouterUtil.match(Arrays.asList("/api/teacher/**", "/api/project/teacher/**", "/api/time/teacher/**"), () -> StpUtil.checkRoleOr("SCHOOL_CHARGE_PERSON", "DEPARTMENT_CHARGE_PERSON", "AUDIT_TEACHER", "INSTRUCTOR_TEACHER"));

        })).addPathPatterns("/**");
    }

//	/**
//     * 注册 [sa-token全局过滤器]
//     */
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//        return new SaServletFilter()
//
//        		// 指定 [拦截路由] 与 [放行路由]
//        		.addInclude("/**").addExclude("/favicon.ico")
//
//        		// 认证函数: 每次请求执行
//        		.setAuth(r -> {
//        			// System.out.println("---------- sa全局认证");
//
//                    // SaRouterUtil.match("/test/test", () -> new Object());
//        		})
//
//        		// 异常处理函数：每次认证函数发生异常时执行此函数
//        		.setError(e -> {
//        			System.out.println("---------- sa全局异常 ");
//        			return AjaxJson.getError(e.getMessage());
//        		})
//
//        		// 前置函数：在每次认证函数之前执行
//        		.setBeforeAuth(r -> {
//        			// ---------- 设置一些安全响应头 ----------
//        			SaHolder.getResponse()
//        			// 服务器名称
//        			.setServer("sa-server")
//        			// 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
//        			.setHeader("X-Frame-Options", "SAMEORIGIN")
//        			// 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
//        			.setHeader("X-Frame-Options", "1; mode=block")
//        			// 禁用浏览器内容嗅探
//        			.setHeader("X-Content-Type-Options", "nosniff")
//        			;
//        		})
//        		;
//    }

}
