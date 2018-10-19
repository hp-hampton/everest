package com.hup.everest.ucenter.dto;

import lombok.Data;

/**
 * @author hupeng on 2018/10/19
 * @version 1.0
 * @Description
 */
@Data
public class GlobalUserDto {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
