package com.yangfan.service;

import com.yangfan.PageBean;
import com.yangfan.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:28
 */
public interface UserService {

    boolean judge(String name,String qqEmail);

    boolean insert(User user, String qqEmail, String code);

    Integer delete(@Param("userid") int userid);

    Integer update(User user);

    PageBean selectAll(Integer page, Integer size);

    User selectOne(@Param("userid") int userid);
}
