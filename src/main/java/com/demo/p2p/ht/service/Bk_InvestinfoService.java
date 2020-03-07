package com.demo.p2p.ht.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.ht.entity.Investinfo;

import java.util.List;

/**
 * <p>
 * 投资表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public interface Bk_InvestinfoService extends IService<Investinfo> {
    public List<Investinfo> findInMoneySum(Integer bmid);
}
