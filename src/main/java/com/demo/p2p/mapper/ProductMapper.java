package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
public interface ProductMapper extends BaseMapper<Product> {
    public List<Product> findProductPage(@Param("map") Map<String,Object> map, Page<Product> page);
    @Select("SELECT * FROM product ORDER BY pmoney DESC LIMIT 3")
    public List<Product> productList();

    @Select("SELECT * FROM product ORDER BY progress DESC  LIMIT 5")
    public List<Product> productList2();

    @Select("SELECT p.ptotalmoney FROM product p WHERE p.bmid=#{bmid}")
    public Long findPtotalmoney(Integer bmid);
}
