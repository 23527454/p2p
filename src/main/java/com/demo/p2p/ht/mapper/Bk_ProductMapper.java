package com.demo.p2p.ht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.ht.entity.Product;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public interface Bk_ProductMapper extends BaseMapper<Product> {

    @Select("SELECT p.ptotalmoney FROM product p WHERE p.bmid=#{bmid}")
    public Long findPtotalmoney(Integer bmid);
}
