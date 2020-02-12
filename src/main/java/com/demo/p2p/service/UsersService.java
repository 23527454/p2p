package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
public interface UsersService extends IService<Users> {
    public Users login(QueryWrapper<Users> queryWrapper);

    public Users checkUsersByCondition(QueryWrapper<Users> queryWrapper);

    public boolean resetPwd(Integer uid,String password);

    public boolean resetPhone(Integer uid,String phone);
}
