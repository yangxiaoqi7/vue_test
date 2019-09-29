package com.yangfan.dao;

import com.yangfan.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:24
 */
@Mapper
public interface PermissionMapper {
    List<Permission> selectPermissionByUsername(@Param("loginName") String loginName);
}
