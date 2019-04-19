package com.rx.authPlat.tenant.controller;


import com.rx.authPlat.tenant.entity.User;
import com.rx.authPlat.tenant.service.TenantUserService;
import com.rx.utils.ResponseMessage;
import com.rx.utils.StateDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @PackageName com.rx.authPlat.tenant.controller
 * @ClassName UserController
 * @Description 用户管理（增删改查用户信息）
 * @Author MengQingJun
 * @Date 2019/4/13
 * @Version 2.0
 **/
@Controller
@RequestMapping("/user")
public class TenantUserController {
    @Autowired
    private TenantUserService tenantUserService;
    @ResponseBody
    @RequestMapping(value="isUser",method = RequestMethod.GET )
    public ResponseMessage isUser(@RequestParam(value = "login_name") String login_name){
        User user= tenantUserService.isUser(login_name);
        if(user==null){
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData("用户不存在");
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(user);
    }

    @ResponseBody
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public ResponseMessage queryUsers(@RequestParam(value = "login_name") String login_name,
                                      @RequestParam(value = "name") String name){
        List<User> list=tenantUserService.queryUsers(login_name,name);
        if(list==null){
            return new ResponseMessage().setCode((StateDict.FAILCODE_NOFOUND)).setMessage(StateDict.FAIL_NOFOUND).setData("用户不存在");
        }
        return  new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(list);
    }

}
