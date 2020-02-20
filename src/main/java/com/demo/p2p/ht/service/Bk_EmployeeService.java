package com.demo.p2p.ht.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.ht.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-18
 */
public interface Bk_EmployeeService extends IService<Employee> {
    public Employee login(QueryWrapper<Employee> queryWrapper);
}
