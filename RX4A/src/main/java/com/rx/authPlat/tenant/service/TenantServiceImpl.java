package com.rx.authPlat.tenant.service;

import com.rx.authPlat.tenant.dao.TenantMapper;
import com.rx.authPlat.tenant.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantMapper tenantMapper;

    /**
     * @param tenant
     * @return i 成功或失败
     * @Description 增加租户
     */
    @Override
    public int addTenant(Tenant tenant) {
        int i = tenantMapper.addTenant(tenant);
        return i;
    }

    /**
     * @param tenant
     * @return i 成功或失败
     * @Description 修改租户
     */
    @Override
    public int updateTenant(Tenant tenant) {

        int i = tenantMapper.updateTenant(tenant);

        return i;
    }

    /**
     * @param tenant
     * @return i 成功或失败
     * @Description 增加或修改租户
     */
    @Override
    public int addOrUpdateTenant(Tenant tenant) {
        Integer i = null;
        String id = tenant.getId();
        if (tenantMapper.findTenantById(id) == null) {
            i = tenantMapper.addTenant(tenant);
        }

        i = tenantMapper.updateTenant(tenant);

        return i;
    }

    /**
     * @param ids
     * @return i 成功或失败
     * @Description 批量删除租户
     */
    @Override
    public int deleteTenants(List<String> ids) {
        int i = tenantMapper.deleteTenantByIds(ids);
        return i;
    }

    /**
     * @param id
     * @return i 成功或失败
     * @Description 根据id删除租户
     */
    @Override
    public int deleteTenantById(String id) {
        int i = tenantMapper.deleteTenantById(id);

        return i;
    }

    /**
     * @param code
     * @param name
     * @return list 查询租户结果集
     * @Description 根据租户代码或名字查询租户
     */
    @Override
    public List<Tenant> queryTenantsByCodeOrName(String code, String name) {
        List<Tenant> list = tenantMapper.queryTenantsByCodeOrName(code, name);
        return list;
    }

}
