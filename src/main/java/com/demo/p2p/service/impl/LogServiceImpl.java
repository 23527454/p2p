package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Log;
import com.demo.p2p.mapper.LogMapper;
import com.demo.p2p.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public boolean addLog(Log log) {
        int result=logMapper.insert(log);
        if (result==1){
            return true;
        }
        return false;
    }
}
