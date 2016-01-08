package com.labassistant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labassistant.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserService sysUserService;

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception e)
            throws Exception {

    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView arg3)
            throws Exception {

    }

    /**
     * 如果返回false 从当前拦截器往回执行所有拦截器的afterCompletion方法，再退回拦截器链 如果返回true
     * 执行下一个拦截器，直到所有拦截器都执行完毕 再运行被拦截的Controller
     * 然后进入拦截器链从最后一个拦截器往回运行所有拦截器的postHandle方法
     * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在所有请求处理之前，验证用户的合法性
        String userid = request.getParameter("userID");

        // todo
        // 因为一开始未考虑周全，使得并不是所有的接口中都带有 userid 信息
        // 但是现在改动接口有点不合适，所以对 userid 为 null 的先放行
        // 以后 if 条件需要删除
        if(userid == null){
            return true;
        }
        if(!sysUserService.isExist(userid)){
            request.getRequestDispatcher("/common/loginOut").forward(request, response);
            return false;
        }
        return true;
    }

}

