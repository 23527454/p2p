package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Users;
import com.demo.p2p.ht.mapper.Bk_UsersMapper;
import com.demo.p2p.ht.service.Bk_UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-20
 */
@Service
public class Bk_UsersServiceImpl extends ServiceImpl<Bk_UsersMapper, Users> implements Bk_UsersService {
    @Resource
    private Bk_UsersMapper bk_usersMapper;
}
