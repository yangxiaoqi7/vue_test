package com.yangfan.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_sys_permission")
public class Permission implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "per_name")
    private String perName;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "parent_code")
    private String parentCode;

    @Column(name = "per_desc")
    private String perDesc;

    @Column(name = "if_vilid")
    private Byte ifVilid;
}
