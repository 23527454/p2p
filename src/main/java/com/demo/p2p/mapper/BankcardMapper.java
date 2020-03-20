package com.demo.p2p.mapper;

import com.demo.p2p.entity.Bankcard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 银行卡管理表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-21
 */
public interface BankcardMapper extends BaseMapper<Bankcard> {
    @Select("SELECT * FROM bankcard WHERE uid = #{uid}")
    public List<Bankcard> getbank(@Param("uid")int uid);

    /**
     * 根据id查询账户
     */
    @Select("SELECT * FROM bankcard WHERE bid = #{bid}")
    public Bankcard getInfo(@Param("bid") int bid);

    @Select("SELECT * FROM bankcard WHERE uID = #{uID}")
    public List<Bankcard> bankcardList(Integer id);

    public int savebankcard(Bankcard bankcard);

    @Select("SELECT * FROM bankcard WHERE uid = #{uid} and khh =#{khh} and cardid = #{cardid}")
    public List<Bankcard> getBanks(@Param("uid")int uid,@Param("khh")String khh,@Param("cardid")String cardid);


}
