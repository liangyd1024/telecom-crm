package com.lyd.telecom.model.resp;

import lombok.Data;

import java.util.List;

/**
 * 描述：uim卡号
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@Data
public class IccIdRespDto extends FileRespDto{

    private String deviceNo;

    private List<String> iccIdList;

}
