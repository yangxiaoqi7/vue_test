package com.yangfan.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:18
 */
@Mapper
public interface UserRoleMapper {
    int addUserRole(int userId,int roleId);
}
