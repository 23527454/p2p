package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Borrowcord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 还款记录表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
public interface Bk_BorrowcordService extends IService<Borrowcord> {
     List<Borrowcord> lssum();//还款未处理的个数
}
