package com.gec.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.mapper.SysUserMapper;
import com.gec.hawaste.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public List<SysUser> selectByRid(long rid) {
        return baseMapper.selectByRid(rid);
    }

    @Override
    public List<SysUser> selectNoRole(long rid, long oid) {
        return baseMapper.selectNoRole(rid, oid);
    }

    @Override
    public SysUser findUserByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper<SysUser>().eq("username", username);
        SysUser user = baseMapper.selectOne(wrapper);
        return user;
    }
}
