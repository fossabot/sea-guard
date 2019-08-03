package club.seaguard.app.backend.common.system.util;

import club.seaguard.app.backend.common.constant.AppConstant;
import club.seaguard.app.backend.common.exception.SeaGuardException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具
 *
 * @author WaTony Weng
 */

public class JwtUtil {

  /**
   * Token过期时间
   */
  public static final long EXPIRE_TIME = 30 * 60 * 1000L;

  /**
   * 校验token是否正确
   *
   * @param token  密钥
   * @param secret 用户密码
   * @return 是否正确
   */
  public static boolean verify(String token, String username, String secret) {
    try {
      // 根据密码生成JWT效验器
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
      // 效验TOKEN
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * @return token中包含的用户名
   */
  public static String getUsername(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 生成签名
   *
   * @param username 用户名
   * @param secret   用户的密码
   * @return 加密的token
   */
  public static String sign(String username, String secret) {
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

  }

  /**
   * 根据request中的token获取用户账号
   *
   * @param request 请求
   * @return username 当前用户
   * @throws SeaGuardException 自定义异常
   */
  public static String getUserNameByToken(HttpServletRequest request) throws SeaGuardException {
    String accessToken = request.getHeader(AppConstant.X_ACCESS_TOKEN);
    String username = getUsername(accessToken);
    if (StringUtils.isEmpty(username)) {
      throw new SeaGuardException("未获取到用户");
    }
    return username;
  }

}
