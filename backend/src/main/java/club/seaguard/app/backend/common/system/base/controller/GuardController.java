package club.seaguard.app.backend.common.system.base.controller;

import club.seaguard.app.backend.common.system.base.entity.GuardEntity;
import club.seaguard.app.backend.common.system.base.service.GuardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用基础控制器
 *
 * @author WaTony Weng
 * @date 2019-07-15
 */

@Slf4j
public class GuardController<T extends GuardEntity, S extends GuardService<T>> {

    @Autowired
    S service;

}
