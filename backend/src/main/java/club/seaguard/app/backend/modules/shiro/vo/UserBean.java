package club.seaguard.app.backend.modules.shiro.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户值对象
 *
 * @author WaTony Weng
 */

@Data
public class UserBean {

  /**
   * 账号
   */
  private String username;

  /**
   * 密码
   */
  private String password;

  /**
   * 角色集合
   */
  private Set<String> roles = new HashSet<>();

  /**
   * 权限集合
   */
  private Set<String> permissions = new HashSet<>();

}
