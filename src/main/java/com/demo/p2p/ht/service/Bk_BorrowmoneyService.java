package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Borrowmoney;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_BorrowmoneyService extends IService<Borrowmoney> {
     List<Borrowmoney> lscount();
}
