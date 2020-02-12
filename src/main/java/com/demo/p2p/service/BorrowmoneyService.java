package com.demo.p2p.service;

import com.demo.p2p.entity.Borrowmoney;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface BorrowmoneyService extends IService<Borrowmoney> {
    public int addMoney(Borrowmoney borrowmoney);
}