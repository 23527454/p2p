package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Trade;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * <p>
 * 交易记录表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
public interface TradeService extends IService<Trade> {
    
    public List<Trade> selectMoney(Integer uid);

    public IPage<Trade> TradeList(Page<Trade> page, QueryWrapper<Trade> wrapper);

    public List<Trade> teacherinfor(Integer id);
}
