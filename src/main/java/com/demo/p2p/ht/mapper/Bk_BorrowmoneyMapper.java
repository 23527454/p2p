package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Borrowmoney;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_BorrowmoneyMapper extends BaseMapper<Borrowmoney> {

    @Select("SELECT id FROM borrowmoney WHERE  bstate = 0;")
    public List<Borrowmoney> lscount();//显示借款未处理数据个数
}
