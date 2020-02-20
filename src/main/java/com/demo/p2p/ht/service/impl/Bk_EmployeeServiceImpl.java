package com.demo.p2p.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.mapper.Bk_EmployeeMapper;
import com.demo.p2p.ht.service.Bk_EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-18
 */
@Service
public class Bk_EmployeeServiceImpl extends ServiceImpl<Bk_EmployeeMapper, Employee> implements Bk_EmployeeService {
    @Resource
    private Bk_EmployeeMapper bk_employeeMapper;

    @Override
    public Employee login(QueryWrapper<Employee> queryWrapper) {
        return bk_employeeMapper.selectOne(queryWrapper);
    }
}
