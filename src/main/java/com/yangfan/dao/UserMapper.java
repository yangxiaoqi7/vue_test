package com.yangfan.dao;

import com.yangfan.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:19
 */
@Mapper
public interface UserMapper {
    User selectByName(@Param("loginName") String loginName);
    Integer insert(User user);
    Integer delete(@Param("userid") int userid);
    Integer update(User user);
    List<User> selectAll();
    User selectOne(@Param("userid") int userid);
    public Integer count();
    public List<User> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
