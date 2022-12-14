package com.gec.hawaste.service.impl;

import com.gec.hawaste.entity.AppVersion;
import com.gec.hawaste.mapper.AppVersionMapper;
import com.gec.hawaste.service.IAppVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService {

}
