package com.demo.p2p.ht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.ht.entity.Investinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 投资表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public interface Bk_InvestinfoMapper extends BaseMapper<Investinfo> {
    @Select("SELECT i.inid,i.userid,i.brrowid,SUM(i.inmoney) AS 'inmoney' FROM investinfo i WHERE i.brrowid=(SELECT p.id FROM product p WHERE p.bmid=#{bmid}) GROUP BY i.userid")
    public List<Investinfo> findInMoneySum(Integer bmid);
}
