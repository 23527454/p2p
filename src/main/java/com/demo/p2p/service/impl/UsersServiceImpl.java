package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Users;
import com.demo.p2p.mapper.UsersMapper;
import com.demo.p2p.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users login(QueryWrapper<Users> queryWrapper) {
        return usersMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer usersNameCount() {
        return this.baseMapper.usersNameCount();
    }


}
