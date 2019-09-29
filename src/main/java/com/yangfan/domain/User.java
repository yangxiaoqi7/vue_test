package com.yangfan.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer userid;

    private String loginName;

    private String password;

    private Byte state;

    private Date createTime;

    private String realname;

    private UserCode userCode;
}
