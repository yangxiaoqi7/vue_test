package com.yangfan.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_code")
public class UserCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qq_email")
    private String qqEmail;
    private String code;
    private Integer status;
}
