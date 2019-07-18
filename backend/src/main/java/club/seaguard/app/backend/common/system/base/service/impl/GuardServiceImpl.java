package club.seaguard.app.backend.common.system.base.service.impl;

import club.seaguard.app.backend.common.system.base.entity.GuardEntity;
import club.seaguard.app.backend.common.system.base.service.GuardService;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Service基础实现类
 *
 * @author WaTony Weng
 * @date 2019-07-15
 */

@Slf4j
public class GuardServiceImpl<M extends BaseMapper<T>, T extends GuardEntity> extends ServiceImpl<M, T> implements GuardService<T> {

}
