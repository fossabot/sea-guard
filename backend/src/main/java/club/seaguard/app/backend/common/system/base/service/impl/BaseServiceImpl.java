package club.seaguard.app.backend.common.system.base.service.impl;

import club.seaguard.app.backend.common.system.base.entity.BaseEntity;
import club.seaguard.app.backend.common.system.base.service.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Service基础实现类
 *
 * @author WaTony Weng
 */

@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T>
  implements BaseService<T> {

}
