package com.demo.p2p.service;

import com.demo.p2p.entity.Investinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 投资表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public interface InvestinfoService extends IService<Investinfo> {

    /**
     * 首页累计投资金额
     * @return
     */
    public Integer sumInmoney();

    /**
     * 盈利金额
     * @return
     */
    public Integer sumProfitmoney();
}
