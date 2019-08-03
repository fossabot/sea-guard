package club.seaguard.app.backend.common.api.vo;

import java.io.Serializable;

import club.seaguard.app.backend.common.constant.HttpConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口返回数据格式
 *
 * @author WaTony Weng
 */

@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class ApiResult<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 成功标志
   */
  @ApiModelProperty(value = "成功标志")
  private boolean success = true;

  /**
   * 返回处理消息
   */
  @ApiModelProperty(value = "返回处理消息")
  private String message = "操作成功";

  /**
   * 返回代码
   */
  @ApiModelProperty(value = "返回代码")
  private Integer code = 0;

  /**
   * 返回数据对象 data
   */
  @ApiModelProperty(value = "返回数据对象")
  private T result;

  /**
   * 时间戳
   */
  @ApiModelProperty(value = "时间戳")
  private long timestamp = System.currentTimeMillis();

  public ApiResult<T> error500(String message) {
    this.message = message;
    this.code = HttpConstant.SC_INTERNAL_SERVER_ERROR_500;
    this.success = false;
    return this;
  }

  public ApiResult<T> success(String message) {
    this.message = message;
    this.code = HttpConstant.SC_OK_200;
    this.success = true;
    return this;
  }

  public static ApiResult<Object> ok() {
    ApiResult<Object> r = new ApiResult<>();
    r.setSuccess(true);
    r.setCode(HttpConstant.SC_OK_200);
    r.setMessage("成功");
    return r;
  }

  public static ApiResult<Object> ok(String msg) {
    ApiResult<Object> r = new ApiResult<>();
    r.setSuccess(true);
    r.setCode(HttpConstant.SC_OK_200);
    r.setMessage(msg);
    return r;
  }

  public static ApiResult<Object> ok(Object data) {
    ApiResult<Object> r = new ApiResult<>();
    r.setSuccess(true);
    r.setCode(HttpConstant.SC_OK_200);
    r.setResult(data);
    return r;
  }

  public static ApiResult<Object> error(String msg) {
    return error(HttpConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
  }

  public static ApiResult<Object> error(int code, String msg) {
    ApiResult<Object> r = new ApiResult<>();
    r.setCode(code);
    r.setMessage(msg);
    r.setSuccess(false);
    return r;
  }

  public static ApiResult<Object> unauthorized(String msg) {
    return error(HttpConstant.SC_UNAUTHORIZED_401, msg);
  }

}
