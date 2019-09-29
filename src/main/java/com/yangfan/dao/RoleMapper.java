package com.yangfan.dao;

import com.yangfan.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:23
 */
public interface RoleMapper extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
