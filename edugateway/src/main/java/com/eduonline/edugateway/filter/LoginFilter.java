package com.eduonline.edugateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 模拟鉴权
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 定义过滤器的类型（生命周期）
     * @return  PRE_TYPE:到达网关前执行
     *          ROUTE_TYPE:经过网关到达服务器之前执行
     *          POST_TYPE:经过服务器之后执行
     *          ERROR_TYPE:发生错误执行
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器执行顺序
     * @return  返回的值越小越先执行
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 决定是否要执行下面的run()方法
     * @return  false:过滤器放行 不执行run()
     *          true :过滤器拦截 执行run()
     */
    @Override
    public boolean shouldFilter() {
        //判断：访问路径包含 eduvideo/getPlayAuth 进行登录校验
        String playUrl = "eduvideo/getPlayAuth";
        //1.获取请求路径uri
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        //2.根据uri判断
        if (!StringUtils.isEmpty(requestURI) &&requestURI.contains(playUrl)){
            //拦截
            return true;
        }else {
            //放行
            return false;
        }
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("run执行了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            //用户未登录 不包含token
            currentContext.setSendZuulResponse(false);//后面不能访问
            //设置状态码
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
