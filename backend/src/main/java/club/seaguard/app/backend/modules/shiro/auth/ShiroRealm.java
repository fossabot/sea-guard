package club.seaguard.app.backend.modules.shiro.auth;

import club.seaguard.app.backend.common.system.util.JwtUtil;
import club.seaguard.app.backend.common.system.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户登录鉴权及获取用户授权
 *
 * @author WaTony Weng
 */

@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

  /**
   * 判断是否JwtToken实例
   *
   * @param token 认证码
   * @return 是否该实例
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  /**
   * 获取认证信息
   *
   * @param principals 角色集合
   * @return 认证信息
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    log.info("doGetAuthorizationInfo start()");

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    Set<String> roleSet = new HashSet<>();
    roleSet.add("管理员");
    info.setRoles(roleSet);

    Set<String> permissionSet = new HashSet<>();
    permissionSet.add("编辑");
    info.addStringPermissions(permissionSet);
    return info;
  }

  /**
   * 身份认证
   *
   * @param auth 用户身份信息
   * @return AuthenticationInfo 认证信息实例
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
    log.info("doGetAuthenticationInfo start()");
    String token = (String) auth.getCredentials();
    if (token == null) {
      throw new AuthenticationException("token为空");
    }
    // 校验token有效性
    LoginUser loginUser = this.checkUserTokenIsEffect(token);
    return new SimpleAuthenticationInfo(loginUser, token, getName());
  }

  /**
   * 校验token的有效性
   *
   * @param token 认证码
   * @throws AuthenticationException 认证异常
   */
  public LoginUser checkUserTokenIsEffect(String token) {
    // 解密获得username，用于和数据库进行对比
    String username = JwtUtil.getUsername(token);
    if (username == null) {
      throw new AuthenticationException("token非法无效!");
    }

    // 查询用户信息
    LoginUser loginUser = new LoginUser();
    loginUser.setUsername("admin");
    return loginUser;
  }

  /**
   * 刷新JWTToken
   *
   * @param token    认证码
   * @param username 账号
   * @param passWord 密码
   * @return boolean 是否成功
   */
  public boolean jwtTokenRefresh(String token, String username, String passWord) {
    return true;
  }

}
