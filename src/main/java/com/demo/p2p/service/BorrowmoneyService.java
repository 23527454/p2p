package com.demo.p2p.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Borrowmoney;

import java.util.List;

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

    public List<Borrowmoney> selHuanKuanList(Integer uid, Page<Borrowmoney> page);

    public Double sumBorrow(Integer uid);
}
