package com.rx.shiro.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private String id;//主键
    private String name;//姓名
    private String login_name;//登录名
    private Integer age;//年龄
    private String address;//地址
    private Integer gender;//性别
    private String email;//邮箱
    private String abbreviation;//简称
    private Date birth_day;//生日
    private String fax;//传真
    private String pic_url;//头像图片地址
    private String comments;//备注
    private String tenant_id;//隔离ID
    private Integer status;//状态(0:有效 1:注销 2:冻结)
    private Integer approval_status;//审批状态
    private Timestamp create_time;//创建时间
    private Timestamp modify_time;//修改时间
    private String create_user;//创建人
    private String modify_user;//修改人
    private String mobile;//手机号码
    private String telephone;//电话号码
    private Long show_order;//排序号
    private Date begin_valid_time;//开始生效时间
    private Date end_valid_time;//结束生效时间
    private String identity_id;//证件号码
    private Integer is_superadmin;//0：superadmin创建的  1：不是superadmin创建的
    private String unit_id;//单位id
    private Integer bind_address_type;//绑定地址类型 1 IP地址 2 MAC地址
    private String bind_ip_address;//绑定的ip地址
    private String bind_mac_address;//绑定的mac地址

    public User() {
    }

    public User(String id, String name, String login_name, Integer age, String address, Integer gender, String email, String abbreviation, Date birth_day, String fax, String pic_url, String comments, String tenant_id, Integer status, Integer approval_status, Timestamp create_time, Timestamp modify_time, String create_user, String modify_user, String mobile, String telephone, Long show_order, Date begin_valid_time, Date end_valid_time, String identity_id, Integer is_superadmin, String unit_id, Integer bind_address_type, String bind_ip_address, String bind_mac_address) {
        this.id = id;
        this.name = name;
        this.login_name = login_name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.abbreviation = abbreviation;
        this.birth_day = birth_day;
        this.fax = fax;
        this.pic_url = pic_url;
        this.comments = comments;
        this.tenant_id = tenant_id;
        this.status = status;
        this.approval_status = approval_status;
        this.create_time = create_time;
        this.modify_time = modify_time;
        this.create_user = create_user;
        this.modify_user = modify_user;
        this.mobile = mobile;
        this.telephone = telephone;
        this.show_order = show_order;
        this.begin_valid_time = begin_valid_time;
        this.end_valid_time = end_valid_time;
        this.identity_id = identity_id;
        this.is_superadmin = is_superadmin;
        this.unit_id = unit_id;
        this.bind_address_type = bind_address_type;
        this.bind_ip_address = bind_ip_address;
        this.bind_mac_address = bind_mac_address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(Integer approval_status) {
        this.approval_status = approval_status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getShow_order() {
        return show_order;
    }

    public void setShow_order(Long show_order) {
        this.show_order = show_order;
    }

    public Date getBegin_valid_time() {
        return begin_valid_time;
    }

    public void setBegin_valid_time(Date begin_valid_time) {
        this.begin_valid_time = begin_valid_time;
    }

    public Date getEnd_valid_time() {
        return end_valid_time;
    }

    public void setEnd_valid_time(Date end_valid_time) {
        this.end_valid_time = end_valid_time;
    }

    public String getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }

    public Integer getIs_superadmin() {
        return is_superadmin;
    }

    public void setIs_superadmin(Integer is_superadmin) {
        this.is_superadmin = is_superadmin;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public Integer getBind_address_type() {
        return bind_address_type;
    }

    public void setBind_address_type(Integer bind_address_type) {
        this.bind_address_type = bind_address_type;
    }

    public String getBind_ip_address() {
        return bind_ip_address;
    }

    public void setBind_ip_address(String bind_ip_address) {
        this.bind_ip_address = bind_ip_address;
    }

    public String getBind_mac_address() {
        return bind_mac_address;
    }

    public void setBind_mac_address(String bind_mac_address) {
        this.bind_mac_address = bind_mac_address;
    }


}
