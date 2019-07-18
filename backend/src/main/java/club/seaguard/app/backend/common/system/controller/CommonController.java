package club.seaguard.app.backend.common.system.controller;

import club.seaguard.app.backend.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用控制器
 *
 * @author WaTony Weng
 * @date 2019-07-15
 */

@Slf4j
@RestController
@RequestMapping("/sys/common")
public class CommonController {

    @GetMapping("/403")
    public Result<?> forbidden() {
        return Result.error("没有权限，请联系管理员授权");
    }

}
