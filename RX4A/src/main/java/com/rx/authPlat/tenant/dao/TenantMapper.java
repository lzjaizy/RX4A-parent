package com.rx.authPlat.tenant.dao;

import com.rx.authPlat.tenant.entity.Tenant;

import java.util.List;

public interface TenantMapper {

    //增加租户
    int addTenant(Tenant tenant);

    //修改租户
    int updateTenant(Tenant tenant);

    //根据id查询租户
    Tenant findTenantById(String id);

    //根据id批量删除租户
    int deleteTenantByIds(List<String> ids);

    //根据删除租户
    int deleteTenantById(String id);

    //根据租户编码或名字查询租户
    List<Tenant> queryTenantsByCodeOrName(String code,String name);



}
