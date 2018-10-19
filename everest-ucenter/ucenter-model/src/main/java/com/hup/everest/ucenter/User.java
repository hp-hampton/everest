package com.hup.everest.ucenter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户全局 ID
     */
    private String guid;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 证件类型： 身份证， 军官证， 护照
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * 证件号码
     */
    @Column(name = "identity_no")
    private String identityNo;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户 ID
     *
     * @return id - 用户 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户 ID
     *
     * @param id 用户 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户全局 ID
     *
     * @return guid - 用户全局 ID
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 设置用户全局 ID
     *
     * @param guid 用户全局 ID
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取证件类型： 身份证， 军官证， 护照
     *
     * @return identity_type - 证件类型： 身份证， 军官证， 护照
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * 设置证件类型： 身份证， 军官证， 护照
     *
     * @param identityType 证件类型： 身份证， 军官证， 护照
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType == null ? null : identityType.trim();
    }

    /**
     * 获取证件号码
     *
     * @return identity_no - 证件号码
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * 设置证件号码
     *
     * @param identityNo 证件号码
     */
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile_phone - 手机号码
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号码
     *
     * @param mobilePhone 手机号码
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}