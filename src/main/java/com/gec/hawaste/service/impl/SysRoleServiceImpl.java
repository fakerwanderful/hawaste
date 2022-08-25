package com.gec.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gec.hawaste.domain.SysRoleDo;
import com.gec.hawaste.entity.*;
import com.gec.hawaste.mapper.SysOfficeMapper;
import com.gec.hawaste.mapper.SysResourceMapper;
import com.gec.hawaste.mapper.SysRoleMapper;
import com.gec.hawaste.mapper.SysRoleResourceMapper;
import com.gec.hawaste.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysOfficeMapper officeMapper;

    @Autowired
    private SysResourceMapper resourceMapper;


    @Override
    public IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Map<String, Object> params) {
        QueryWrapper<SysRoleDo> query = new QueryWrapper<>();
        //apply拼接固定sql，底层通过预编译处理
        query.apply("sr.del_flag=0 AND sr.office_id=so.id")
                .eq(params.containsKey("dataScope") && !ObjectUtils.isEmpty(params.get("dataScope")), "sr.data_scope", params.get("dataScope"))
                .like(params.containsKey("name") && !ObjectUtils.isEmpty(params.get("name")), "sr.NAME", params.get("name"))
                .like(params.containsKey("remakrs") && !ObjectUtils.isEmpty(params.get("remakrs")), "sr.remakrs", params.get("remakrs"))
                .eq(params.containsKey("id") && !ObjectUtils.isEmpty(params.get("id")), "sr.id", params.get("id"));
        return baseMapper.selectByCondition(page, query);
    }

    @Override
    public SysRoleDo selectOne(Long id) {
        //查询角色信息
        SysRoleDo role = baseMapper.selectByRid(id);
        //查询角色授权资源信息
        role.setResources(resourceMapper.selectByRid(id));
        //如果是按明细设置，查询角色授权公司信息(跨机构 )
        if ("9".equals(role.getDataScope())){
            role.setOffices(officeMapper.selectByRid(id));
        }
        return role;
    }

    @Override
    public List<SysRole> selectByUid(Long uid) {
        return baseMapper.selectByUid(uid);
    }

}
