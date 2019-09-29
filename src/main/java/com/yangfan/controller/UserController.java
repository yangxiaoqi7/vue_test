package com.yangfan.controller;

import com.yangfan.PageBean;
import com.yangfan.domain.User;
import com.yangfan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 22:33
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/loginVal",method = RequestMethod.POST)
    public String loginVal(@RequestBody User user){
        String username=user.getLoginName();
        String password=user.getPassword();

        if(username!=""&&username!=null&&password!=""&&password!=null){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                subject.login(token);
                if(subject.isAuthenticated()){
                    return "success";
                }else {
                    return "fail";
                }
            }catch (Exception e){
                return "fail";
            }
        }
        return "值不能为空";
    }


    @RequestMapping(value = "/judge",method = RequestMethod.POST)
    public String judge(@RequestBody User user){
        String name=user.getLoginName();
        String qq=user.getUserCode().getQqEmail();
        if(userService.judge(name,qq)){
            return "success";
        }
        return "fail";
    }


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(@RequestBody User user){
        System.out.println(user);
        boolean s=userService.insert(user,user.getUserCode().getQqEmail(),user.getUserCode().getCode());
        if(s){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id){
        Integer delete = userService.delete(id);
        //System.out.println(delete);
        if(delete>0){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@RequestBody User user){
        Integer update = userService.update(user);
        if(update>0){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/selectAll/{page}/{size}")
    public PageBean selectAll(@PathVariable Integer page, @PathVariable Integer size){
        return userService.selectAll(page,size);
    }

    @RequestMapping(value = "/selectOne/{id}")
    public User selectOne(@PathVariable Integer id){
        return userService.selectOne(id);
    }





    //关系
    @RequiresPermissions(value = {"user_insert"})           //注解设置权限
    @RequestMapping(value = "/registered")
    public String registered(){
        return "insert";
    }

    @RequiresPermissions(value = {"user_delete"})           //注解设置权限
    @RequestMapping(value = "/del")
    public String del(){
        return "delete";
    }

    @RequiresPermissions(value = {"user_update"})            //注解设置权限
    @RequestMapping(value = "/upd")
    public String upd(){

        return "update";
    }

    @RequiresPermissions(value = {"user_select"})             //注解设置权限
    @RequestMapping(value = "/sel")
    public String sel(){

        return "selectAll";
    }

    //没有权限
    @RequestMapping(value = "/noPermission")
    public String noPermission(){
        return "noPermission";
    }
}