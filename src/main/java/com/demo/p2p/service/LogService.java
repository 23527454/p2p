package com.demo.p2p.service;

import com.demo.p2p.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public interface LogService extends IService<Log> {
    public boolean addLog(Log log);
}
