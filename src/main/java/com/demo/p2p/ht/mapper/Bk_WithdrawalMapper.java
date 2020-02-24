package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Withdrawal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现管理表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_WithdrawalMapper extends BaseMapper<Withdrawal> {
    public List<Withdrawal> sellist(Map<String, Object> map);
    public double sumtxmoney(Map<String, Object> map);
    public double sumdzmoney(Map<String, Object> map);
    public double sumsxf(Map<String, Object> map);
}
