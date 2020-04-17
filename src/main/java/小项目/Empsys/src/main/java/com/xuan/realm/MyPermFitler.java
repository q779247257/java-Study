package com.xuan.realm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 轩轩
 * @Date: 2020/4/16 21:56
 * @description:
 */
public class MyPermFitler extends PermissionsAuthorizationFilter {
    // 权限验证不通过，在该方法中确定进一步的操作
    // ajax请求，需要返回json格式数据
    // 非ajax请求，跳转到指定的资源
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        // 如果是ajax方式，在请求头中会自动增加X-Requested-With 的key-value数据
        String header = req.getHeader("X-Requested-With");

        if(header != null && !header.equals("") && header.equals("XMLHttpRequest")){
            // 手动转为json格式字符串，返回浏览器端
            ObjectMapper mapper = new ObjectMapper();
            String jsonInfo = mapper.writeValueAsString("访问没有权限");
            response.getWriter().write("1234");
        }else{
            // 非ajax方式，重定向到指定的资源
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
        }

        // 验证不通过
        return false;
    }
}
