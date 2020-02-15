package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Product;
import com.demo.p2p.mapper.ProductMapper;
import com.demo.p2p.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
