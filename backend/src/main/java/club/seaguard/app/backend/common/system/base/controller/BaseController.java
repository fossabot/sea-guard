package club.seaguard.app.backend.common.system.base.controller;

import club.seaguard.app.backend.common.system.base.entity.BaseEntity;
import club.seaguard.app.backend.common.system.base.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用基础控制器
 *
 * @author WaTony Weng
 */

@Slf4j
public class BaseController<T extends BaseEntity, S extends BaseService<T>> {

  @Autowired
  S service;

}
