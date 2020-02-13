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
    public Users getUsersById(int id);

    public int insertucertnum(Users users);

    public int saveUser(Users users);

    public Users login(QueryWrapper<Users> queryWrapper);


    /**
     * 首页注册人数
     * @return
     */
    public Integer usersNameCount();

    public Users checkUsersByCondition(QueryWrapper<Users> queryWrapper);

    public boolean resetPwd(Integer uid,String password);


    public boolean resetPhone(Integer uid,String phone);
}
