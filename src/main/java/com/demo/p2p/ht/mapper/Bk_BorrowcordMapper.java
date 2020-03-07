package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Borrowcord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 还款记录表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
public interface Bk_BorrowcordMapper extends BaseMapper<Borrowcord> {

    @Select("SELECT boid FROM borrowcord WHERE DATE_SUB(CURDATE(), INTERVAL 10 DAY) <= DATE(bdate)")
    public List<Borrowcord> lssum();//还款未处理的个数
}
