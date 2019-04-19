package com.rx.authPlat.tenant.controller;

import com.rx.authPlat.tenant.entity.Tenant;
import com.rx.authPlat.tenant.service.TenantService;
import com.rx.utils.ResponseMessage;
import com.rx.utils.StateDict;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @PackageName com.rx.authPlat.tenant.controller
 * @ClassName TenantController
 * @Description 租户管理(增删改查租户信息)
 * @Author mengqingjun
 * @Date 2019-04-11
 * @Version 2.0
 **/
@Controller
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * @param tenant
     * @return ResponseMessage 成功或失败
     * @Description 增加用户
     */
    @ResponseBody
    @RequestMapping(value = "/tenant", method = RequestMethod.POST)
    public ResponseMessage addTenant(@RequestBody Tenant tenant) {
        int i = tenantService.addTenant(tenant);
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(tenant);
    }

    /**
     * @param tenant
     * @return ResponseMessage 成功或失败
     * @Description 修改用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/tenant", method = RequestMethod.PUT)
    public ResponseMessage updateTenant(@RequestBody Tenant tenant) {
        int i = tenantService.updateTenant(tenant);
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(i);
    }

    /**
     * @param tenant
     * @return ResponseMessage 成功或失败
     * @Description 增加或修改用户
     */
    @ResponseBody
    @RequestMapping(value = "/mixedTenant", method = RequestMethod.POST)
    public ResponseMessage addOrUpdateTenant(@RequestBody Tenant tenant) {
        //CheckUtils.checkTenant(tenant);
        int i = tenantService.addOrUpdateTenant(tenant);
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(tenant);
    }

    /**
     * @param ids
     * @return ResponseMessage 成功或失败
     * @Description 批量删除租户
     */
    @ResponseBody
    @RequestMapping(value = "/tenants", method = RequestMethod.DELETE)
    public ResponseMessage deleteTenants(@RequestBody List<String> ids) {
        int i = tenantService.deleteTenants(ids);
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(i);
    }

    /**
     * @param id
     * @return ResponseMessage 成功或失败
     * @Description 根据id删除租户
     */
    @ResponseBody
    @RequestMapping(value = "/tenant", method = RequestMethod.DELETE)
    public ResponseMessage deleteTenant(@RequestParam(value = "id") String id) {
        //
        int i = tenantService.deleteTenantById(id);
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(i);
    }

    /**
     * @param code
     * @param name
     * @return ResponseMessage 成功或失败
     * @Description 查询租户信息
     */
    @ResponseBody
    @RequestMapping(value = "/tenant", method = RequestMethod.GET)
    public ResponseMessage queryTenants(@RequestParam(value = "code") String code,
                                        @RequestParam(value = "name") String name) {
        List<Tenant> list = tenantService.queryTenantsByCodeOrName(code, name);
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(list);
    }
}
