package com.gec.hawaste.service;

import com.gec.hawaste.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    int deleteBatch(long rid,Long[] ids);
    boolean insertBatch(long rid, List<Long> ids);

}
