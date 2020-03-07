package com.demo.p2p.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
public interface ProductService extends IService<Product> {
    public Page<Product> findProductPage(Map<String,Object> map,Page<Product> page);
    public Product selById(int id);

    public List<Product> productList();
    public List<Product> productList2();

    public Long findPtotalmoney(Integer bmid);
}
