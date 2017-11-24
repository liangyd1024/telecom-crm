package com.lyd.telecom.model.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 描述：证件号信息响应
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@Data
@ToString(callSuper = true)
public class CertNoRespDto extends FileRespDto{

    private String deviceNo;

    private List<String> certNoList;

}
