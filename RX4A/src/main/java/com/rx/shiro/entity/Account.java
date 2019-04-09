package com.rx.shiro.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Account {

  private String id;//主键
  private String name;//姓名
  private String login_name;//登录名
  private String salt;//密码盐
  private String open_id;//Oauth2
  private Integer open_secret;//Oauth2
  private Integer is_temporary;//是否临时账户
  private Timestamp start_date;//临时账户开始时间
  private Timestamp end_date;//临时账户结束时间
  private Integer status;//状态：0-有效 1-冻结 2-无效 3-锁定
  private Integer approval_status;//'审批状态 0-审批通过 1-审批驳回 2-插入提交待审批 3-更新待审批 4-删除待审批
  private String create_user;//创建人
  private String modify_user;//修改人
  private String tenant_id;//隔离ID
  private Timestamp create_time;//创建时间
  private Timestamp modify_time;//修改时间
  private String password;//加密密码
  private String email;//邮箱
  private String validata_code;//找回密码的唯一标示
  private Timestamp validata_out_date;//找回密码唯一标示过期时间
  private String last_login_ip;//上次登录ip地址
  private Timestamp last_login_date;//最后一次登录时间
  private Timestamp login_time;//登录时间
  private Timestamp modify_password_time;//修改密码时间
  private Date begin_valid_time;//开始生效时间
  private Date end_valid_time;//结束生效时间
  private String identity_id;//证件号码
  private Integer is_superadmin;//superadmin所建账号：0  其他：1
  private String unit_id;//单位id

  public Account() {
  }

  public Account(String login_name, String salt, String password) {
    this.login_name = login_name;
    this.salt = salt;
    this.password = password;
  }

  public Account(String id, String name, String login_name, String salt, String open_id,
      Integer open_secret, Integer is_temporary, Timestamp start_date, Timestamp end_date,
      Integer status, Integer approval_status, String create_user, String modify_user,
      String tenant_id, Timestamp create_time, Timestamp modify_time, String password, String email,
      String validata_code, Timestamp validata_out_date, String last_login_ip,
      Timestamp last_login_date, Timestamp login_time, Timestamp modify_password_time,
      Date begin_valid_time, Date end_valid_time, String identity_id, Integer is_superadmin,
      String unit_id) {
    this.id = id;
    this.name = name;
    this.login_name = login_name;
    this.salt = salt;
    this.open_id = open_id;
    this.open_secret = open_secret;
    this.is_temporary = is_temporary;
    this.start_date = start_date;
    this.end_date = end_date;
    this.status = status;
    this.approval_status = approval_status;
    this.create_user = create_user;
    this.modify_user = modify_user;
    this.tenant_id = tenant_id;
    this.create_time = create_time;
    this.modify_time = modify_time;
    this.password = password;
    this.email = email;
    this.validata_code = validata_code;
    this.validata_out_date = validata_out_date;
    this.last_login_ip = last_login_ip;
    this.last_login_date = last_login_date;
    this.login_time = login_time;
    this.modify_password_time = modify_password_time;
    this.begin_valid_time = begin_valid_time;
    this.end_valid_time = end_valid_time;
    this.identity_id = identity_id;
    this.is_superadmin = is_superadmin;
    this.unit_id = unit_id;
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

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getOpen_id() {
    return open_id;
  }

  public void setOpen_id(String open_id) {
    this.open_id = open_id;
  }

  public Integer getOpen_secret() {
    return open_secret;
  }

  public void setOpen_secret(Integer open_secret) {
    this.open_secret = open_secret;
  }

  public Integer getIs_temporary() {
    return is_temporary;
  }

  public void setIs_temporary(Integer is_temporary) {
    this.is_temporary = is_temporary;
  }

  public Timestamp getStart_date() {
    return start_date;
  }

  public void setStart_date(Timestamp start_date) {
    this.start_date = start_date;
  }

  public Timestamp getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Timestamp end_date) {
    this.end_date = end_date;
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

  public String getTenant_id() {
    return tenant_id;
  }

  public void setTenant_id(String tenant_id) {
    this.tenant_id = tenant_id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getValidata_code() {
    return validata_code;
  }

  public void setValidata_code(String validata_code) {
    this.validata_code = validata_code;
  }

  public Timestamp getValidata_out_date() {
    return validata_out_date;
  }

  public void setValidata_out_date(Timestamp validata_out_date) {
    this.validata_out_date = validata_out_date;
  }

  public String getLast_login_ip() {
    return last_login_ip;
  }

  public void setLast_login_ip(String last_login_ip) {
    this.last_login_ip = last_login_ip;
  }

  public Timestamp getLast_login_date() {
    return last_login_date;
  }

  public void setLast_login_date(Timestamp last_login_date) {
    this.last_login_date = last_login_date;
  }

  public Timestamp getLogin_time() {
    return login_time;
  }

  public void setLogin_time(Timestamp login_time) {
    this.login_time = login_time;
  }

  public Timestamp getModify_password_time() {
    return modify_password_time;
  }

  public void setModify_password_time(Timestamp modify_password_time) {
    this.modify_password_time = modify_password_time;
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

  /**
   * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
   */
  public String getCredentialsSalt() {
    return this.login_name + this.salt;
  }
}
