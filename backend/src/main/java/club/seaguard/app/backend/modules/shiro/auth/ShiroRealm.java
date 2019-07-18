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
 * @date 2019-07-15
 */

@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo start()");
        LoginUser sysUser = null;
        String username = null;
        if (principals != null) {
            sysUser = (LoginUser) principals.getPrimaryPrincipal();
            username = sysUser.getUsername();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // TODO 从Service中获取
        Set<String> roleSet = new HashSet<>();
        roleSet.add("管理员");
        info.setRoles(roleSet);

        Set<String> permissionSet = new HashSet<>();
        permissionSet.add("编辑");
        info.addStringPermissions(permissionSet);
        return info;
    }

    /**
     * 功能：用来进行身份认证，也就是说验证用户输入的账号和密码是否正确，获取身份验证信息，错误抛出异常
     *
     * @param auth 用户身份信息
     * @return AuthenticationInfo 用户信息实例
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
     * @param token
     */
    public LoginUser checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("admin");
        // TODO 根据用户名查询用户
        return loginUser;
    }

    /**
     * JWTToken刷新生命周期
     *
     * @param username 账号
     * @param passWord 密码
     * @return boolean 是否成功
     */
    public boolean jwtTokenRefresh(String token, String username, String passWord) {
        return true;
    }

}
