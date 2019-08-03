package club.seaguard.app.backend.common.system.controller;

import club.seaguard.app.backend.common.api.vo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用控制器
 *
 * @author WaTony Weng
 */

@Slf4j
@RestController
@RequestMapping("/sys/common")
public class CommonController {

  @GetMapping("/403")
  public ApiResult<Object> forbidden() {
    return ApiResult.error("您尚无操作权限，请联系管理员授权");
  }

}
