package com.rx.authPlat.tenant.service;

import com.rx.authPlat.tenant.entity.Tenant;

import java.util.List;

public interface TenantService {

    //增加租户
    int addTenant(Tenant tenant);

    //修改租户
    int updateTenant(Tenant tenant);

    //增加或修改租户
    int addOrUpdateTenant(Tenant tenant);

    //根据id删除租户
    int deleteTenantById(String id);

    //批量删除租户
    int deleteTenants(List<String> ids);

    //根据租户编码或名字查询租户
    List<Tenant> queryTenantsByCodeOrName(String code, String name);


}
