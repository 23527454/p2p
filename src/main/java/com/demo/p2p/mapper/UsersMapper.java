package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
public interface UsersMapper extends BaseMapper<Users> {

    @Update("update users set upassword=#{password} where uid=#{uid}")
    public Integer resetPwd(@Param("uid") Integer uid, @Param("password") String password);

    @Update("update users set uphonenumber=#{phone} where uid=#{uid}")
    public Integer resetPhone(@Param("uid") Integer uid, @Param("phone") String phone);
}
