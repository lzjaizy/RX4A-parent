package com.rx.authPlat.user.controller;


import com.rx.authPlat.user.entity.Role;
import com.rx.authPlat.user.entity.User;
import com.rx.authPlat.user.service.TenantUserService;
import com.rx.utils.ResponseMessage;
import com.rx.utils.StateDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @PackageName com.rx.authPlat.user.controller
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
    @RequestMapping(value = "isUser", method = RequestMethod.GET)
    public ResponseMessage isUser(@RequestParam(value = "login_name") String login_name) {
        User user = tenantUserService.isUser(login_name);
        if (user == null) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData("用户不存在");
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(user);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseMessage queryUsers(@RequestParam(value = "login_name") String login_name,
                                      @RequestParam(value = "name") String name) {
        List<User> list = tenantUserService.queryUsers(login_name, name);
        if (list == null) {
            return new ResponseMessage().setCode((StateDict.FAILCODE_NOFOUND)).setMessage(StateDict.FAIL_NOFOUND).setData("用户不存在");
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(list);
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles",method = RequestMethod.GET)
    public ResponseMessage queryUserRoles(@RequestParam(value = "id") String id){
        List<Role> list=tenantUserService.queryUserRoles(id);
        if (list == null) {
            return new ResponseMessage().setCode((StateDict.FAILCODE_NOFOUND)).setMessage(StateDict.FAIL_NOFOUND).setData("用户角色不存在");
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(list);
    }

    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseMessage addUser(@RequestBody User user) {
        int i = tenantUserService.addUser(user);
        if (i == 1) {
            return  new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(user);
        }
         return new ResponseMessage().setCode((StateDict.FAILCODE_NOFOUND)).setMessage(StateDict.FAIL_NOFOUND).setData("添加用户失败");
    }

}
