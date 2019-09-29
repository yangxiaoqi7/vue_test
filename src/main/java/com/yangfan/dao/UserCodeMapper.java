package com.yangfan.dao;

import com.yangfan.domain.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:22
 */
public interface UserCodeMapper extends JpaRepository<UserCode,Integer> {
    UserCode findByQqEmail(String qqEmail);
}
