package com.demo.p2p.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.ht.entity.Product;
import com.demo.p2p.ht.mapper.Bk_ProductMapper;
import com.demo.p2p.ht.service.Bk_ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Service
public class Bk_ProductServiceImpl extends ServiceImpl<Bk_ProductMapper, Product> implements Bk_ProductService {
    @Resource
    private Bk_ProductMapper bk_productMapper;

    @Override
    public Long findPtotalmoney(Integer bmid) {
        return bk_productMapper.findPtotalmoney(bmid);
    }
}
