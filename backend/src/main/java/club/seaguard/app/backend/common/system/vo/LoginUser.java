package club.seaguard.app.backend.common.system.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录用户值模型
 *
 * @author WaTony Weng
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUser {

  /**
   * 登录用户编号
   */
  private String id;

  /**
   * 登录用户账号
   */
  private String username;

  /**
   * 登录用户名字
   */
  private String realName;

  /**
   * 当前登录部门编码
   */
  private String orgCode;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 生日
   */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  /**
   * 性别
   */
  private Integer sex;

  /**
   * 电子邮件
   */
  private String email;

  /**
   * 电话
   */
  private String phone;

  /**
   * 状态
   */
  private Integer status;

}
