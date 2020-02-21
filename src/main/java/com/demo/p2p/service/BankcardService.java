package com.demo.p2p.service;

import com.demo.p2p.entity.Bankcard;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 银行卡管理表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-21
 */
public interface BankcardService extends IService<Bankcard> {
    public List<Bankcard> getbank( int uid);
}
