package club.seaguard.app.backend.common.system.api;

import club.seaguard.app.backend.common.system.vo.DictModel;
import club.seaguard.app.backend.common.system.vo.LoginUser;

import java.sql.SQLException;
import java.util.List;

/**
 * 底层共通业务API
 *
 * @author WaTony Weng
 */

public interface BaseApi {

  /**
   * 添加日志
   *
   * @param content     内容
   * @param logType     日志类型
   * @param operateType 操作类型
   */
  void addLog(String content, Integer logType, Integer operateType);

  /**
   * 根据用户账号查询登录用户信息
   *
   * @param username 用户名
   * @return 登录用户
   */
  public LoginUser getUserByName(String username);

  /**
   * 通过用户账号查询角色集合
   *
   * @param username 用户名
   * @return 角色列表
   */
  public List<String> getRolesByUsername(String username);

  /**
   * 获取当前数据库类型
   *
   * @return 数据库类型
   * @throws SQLException 数据库异常
   */
  public String getDatabaseType() throws SQLException;

  /**
   * 获取数据字典
   *
   * @param code 字典编码
   * @return 数据字典列表
   */
  public List<DictModel> queryDictItemsByCode(String code);

}
