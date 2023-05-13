package com.dawn.encryption.waring.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dawn.encryption.waring.domain.WaringDomain;
import com.dawn.encryption.waring.entity.WaringEntity;
import com.dawn.encryption.waring.mapper.WaringMapper;
import com.dawn.encryption.waring.service.IWaringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaringService implements IWaringService {

    @Autowired
    WaringMapper waringMapper;

    @Override
    public List<WaringDomain> findAll() {
        QueryWrapper<WaringEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<WaringEntity> waringEntities = waringMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(waringEntities, WaringDomain.class);
    }
}
