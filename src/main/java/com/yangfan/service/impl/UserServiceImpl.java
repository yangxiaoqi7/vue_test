package com.yangfan.service.impl;

import com.yangfan.PageBean;
import com.yangfan.dao.RoleMapper;
import com.yangfan.dao.UserCodeMapper;
import com.yangfan.dao.UserMapper;
import com.yangfan.dao.UserRoleMapper;
import com.yangfan.domain.User;
import com.yangfan.domain.UserCode;
import com.yangfan.service.UserService;
import com.yangfan.untils.EmailUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:31
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserCodeMapper userCodeMapper;
    @Resource
    private EmailUtils emailUtils;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public boolean judge(String name, String qqEmail) {
        User user = userMapper.selectByName(name);
        if(user==null){
            String s = emailUtils.sendEmail(qqEmail);

            UserCode email = userCodeMapper.findByQqEmail(qqEmail);
            if(email==null){
                email.setQqEmail(qqEmail);
                email.setStatus(1);
                email.setCode(s);
                userCodeMapper.save(email);
            }else {
                email.setCode(s);
                userCodeMapper.saveAndFlush(email);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(User user, String qqEmail, String code) {
        UserCode email = userCodeMapper.findByQqEmail(qqEmail);

        if(code.equals(email.getCode())){
            String hashAlgorithName = "MD5";
            String password = user.getPassword();
            int hashIterations =1024;
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getLoginName());
            SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);

            user.setPassword(simpleHash.toString());
            user.setCreateTime(new Date());
            user.setState(Byte.valueOf("1"));

            if(userMapper.insert(user)>0){
                userRoleMapper.addUserRole(user.getUserid(),roleMapper.findByRoleName("user_select").getRoleId());
                return true;
            }
        }
        return false;
    }


    @Override
    public Integer delete(int userid) {

        return userMapper.delete(userid);
    }

    @Override
    public Integer update(User user) {
        String hashAlgorithName = "MD5";
        String password = user.getPassword();
        int hashIterations =1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getLoginName());
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);

        user.setPassword(simpleHash.toString());
        return userMapper.update(user);
    }

    @Override
    public PageBean selectAll(Integer page, Integer size) {
        int startIndex=(page-1)*size;
        List<User> list = userMapper.selectByPage(startIndex, size);

        PageBean pageBean=new PageBean();
        pageBean.setNowPage(page);
        pageBean.setPageSize(size);
        pageBean.setRowsNum(userMapper.count());
        pageBean.setList(list);
        pageBean.cal();  //调用执行方法
        return pageBean;
    }
    @Override
    public User selectOne(int userid) {

        return userMapper.selectOne(userid);
    }
}
