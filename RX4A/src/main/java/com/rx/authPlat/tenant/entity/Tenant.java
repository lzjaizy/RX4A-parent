package com.rx.authPlat.tenant.entity;

import java.sql.Timestamp;

/**
 * 租户实体类
 */
public class Tenant {

    private String id;
    private String name;
    private String code;
    private String abbreviation;
    private String tenantLevel;
    private Integer maxLimit;
    private Integer contactPerson;
    private Integer address;
    private Integer contactPhone;
    private Integer email;
    private Timestamp effectiveTime;
    private Timestamp expirationTime;
    private Integer maxUserNumber;
    private String domainName;
    private String logoUrl;
    private String backgroudUrl;
    private String showName;
    private Integer status;
    private String comments;
    private Long showOrder;
    private String createUser;
    private String modifyUser;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private int isDel;

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getTenantLevel() {
        return tenantLevel;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public Integer getContactPerson() {
        return contactPerson;
    }

    public Integer getAddress() {
        return address;
    }

    public Integer getContactPhone() {
        return contactPhone;
    }

    public Integer getEmail() {
        return email;
    }

    public Timestamp getEffectiveTime() {
        return effectiveTime;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public Integer getMaxUserNumber() {
        return maxUserNumber;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getBackgroudUrl() {
        return backgroudUrl;
    }

    public String getShowName() {
        return showName;
    }

    public Integer getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }



    public String getCreateUser() {
        return createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setTenantLevel(String tenantLevel) {
        this.tenantLevel = tenantLevel;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setContactPerson(Integer contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public void setContactPhone(Integer contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public void setEffectiveTime(Timestamp effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setMaxUserNumber(Integer maxUserNumber) {
        this.maxUserNumber = maxUserNumber;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setBackgroudUrl(String backgroudUrl) {
        this.backgroudUrl = backgroudUrl;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", tenantLevel='" + tenantLevel + '\'' +
                ", maxLimit=" + maxLimit +
                ", contactPerson=" + contactPerson +
                ", address=" + address +
                ", contactPhone=" + contactPhone +
                ", email=" + email +
                ", effectiveTime=" + effectiveTime +
                ", expirationTime=" + expirationTime +
                ", maxUserNumber=" + maxUserNumber +
                ", domainName='" + domainName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", backgroudUrl='" + backgroudUrl + '\'' +
                ", showName='" + showName + '\'' +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", showOrder='" + showOrder + '\'' +
                ", createUser='" + createUser + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", isDel=" + isDel +
                '}';
    }
}
