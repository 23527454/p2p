package com.demo.p2p.service;

import com.demo.p2p.entity.Borrowcord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Borrowmoney;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
public interface BorrowcordService extends IService<Borrowcord> {
    public List<Borrowcord> selInfo(Map<String, Object> map);
    public Double selSum(Map<String, Object> map);
}
