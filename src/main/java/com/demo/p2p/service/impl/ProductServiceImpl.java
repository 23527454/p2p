package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.entity.Product;
import com.demo.p2p.mapper.ProductMapper;
import com.demo.p2p.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Page<Product> findProductPage(Map<String, Object> map,Page<Product> page) {
        return page.setRecords(productMapper.findProductPage(map,page));
    }

    @Override
    public Product selById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> productList() {
        return this.baseMapper.productList();
    }

    @Override
    public List<Product> productList2() {
        return this.baseMapper.productList2();
    }

    @Override
    public Long findPtotalmoney(Integer bmid) {
        return productMapper.findPtotalmoney(bmid);
    }
}
