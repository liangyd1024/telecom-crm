package com.lyd.telecom.model.req;

import lombok.Data;

/**
 * 描述：用户信息
 * <p>
 * #
 * </p>
 * UserReqDto: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@Data
public class UserReqDto {

    private String userNo;

    private String pwd;

    private String smsCode;

    private String sessionId;

}
