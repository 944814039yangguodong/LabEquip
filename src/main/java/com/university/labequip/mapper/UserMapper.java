package com.university.labequip.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.university.labequip.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByIdIncludeDeleted(String sql);

    void forceDelete(String sql);

}
