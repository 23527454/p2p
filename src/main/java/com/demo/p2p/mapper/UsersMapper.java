package com.demo.p2p.mapper;

import com.demo.p2p.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
public interface UsersMapper extends BaseMapper<Users> {
      @Select("SELECT COUNT(unickname) FROM users")
      public Integer usersNameCount();
}
