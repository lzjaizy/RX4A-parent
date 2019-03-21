package com.rx.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rx.demo.dao.UserMapper;
import com.rx.demo.entity.PageEntity;
import com.rx.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserMemberService")
public class UserMemberServiceImpl implements UserMemberService {

    @Resource
    private UserMapper userMapper;


    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public Page<User> getUsers(PageEntity pageEntity) {
        try {
            PageHelper.startPage(pageEntity.getPageNo(),pageEntity.getPageSize());

        }catch (Exception e){
            e.printStackTrace();
        }
        return userMapper.selectAllUsers();
    }

    public boolean addUser(User record){
        boolean result = false;
        try {
            userMapper.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}