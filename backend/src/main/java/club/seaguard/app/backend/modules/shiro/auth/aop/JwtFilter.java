package club.seaguard.app.backend.modules.shiro.auth.aop;

import club.seaguard.app.backend.common.constant.AppConstant;
import club.seaguard.app.backend.modules.shiro.auth.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权登录拦截器
 *
 * @author WaTony Weng
 * @see org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
 */

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

  /**
   * 是否允许访问
   *
   * @param request     请求
   * @param response    响应
   * @param mappedValue 映射值
   * @return 是否允许
   */
  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    try {
      executeLogin(request, response);
      return true;
    } catch (Exception e) {
      throw new AuthenticationException("Token失效，请重新登录", e);
    }
  }

  /**
   * 执行登录
   *
   * @param request  请求
   * @param response 响应
   * @return 是否登录成功
   */
  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String token = httpServletRequest.getHeader(AppConstant.X_ACCESS_TOKEN);
    JwtToken jwtToken = new JwtToken(token);
    // 提交给Realm进行登入，如果错误他会抛出异常并被捕获
    getSubject(request, response).login(jwtToken);
    // 如果没有抛出异常则代表登入成功，返回true
    return true;
  }

  /**
   * 对跨域提供支持
   *
   * @param request  请求
   * @param response 响应
   * @return 是否支持
   */
  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
    httpServletResponse.setHeader("Access-Control-Allow-Headers",
      httpServletRequest.getHeader("Access-Control-Request-Headers"));
    // 跨域时会首先发送一个Option请求，这里我们给option请求直接返回正常状态
    if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
      httpServletResponse.setStatus(HttpStatus.OK.value());
      return false;
    }
    return super.preHandle(request, response);
  }

}
